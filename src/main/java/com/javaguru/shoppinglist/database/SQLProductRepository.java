package com.javaguru.shoppinglist.database;

import com.javaguru.shoppinglist.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Profile({"sql"})
public class SQLProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    SQLProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long insertProduct(Product product) {
        String query = "INSERT INTO products (name, price, category, discount, description) VALUES"
                + "(?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setString(3, product.getCategory());
            preparedStatement.setDouble(4, product.getDiscount());
            preparedStatement.setString(5, product.getDescription());
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        String query = "SELECT * FROM products WHERE id=" + id;

        List<Product> products = jdbcTemplate.query(query,
                new BeanPropertyRowMapper(Product.class));
        if (!products.isEmpty()) {
            return Optional.ofNullable(products.get(0));
        }

        return Optional.empty();
    }

    @Override
    public boolean existInRepositoryByName(String name) {
        String query = "SELECT CASE WHEN count(*) > 0 " +
                "THEN true ELSE false END " +
                "FROM products WHERE name=" + name;
        return jdbcTemplate.queryForObject(query, Boolean.class);
    }
}
