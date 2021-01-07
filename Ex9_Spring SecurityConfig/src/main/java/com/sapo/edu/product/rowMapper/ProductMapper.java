package com.sapo.edu.product.rowMapper;

import com.sapo.edu.product.model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductMapper {
    public static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();

            product.setId(rs.getInt("id"));
            product.setImg_link(rs.getString("img_link"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setDescription(rs.getString("description"));
            product.setQuantity(rs.getInt("quantity"));
            product.setSale_number(rs.getInt("sale_number"));
            product.setCategory_id(rs.getInt("category_id"));

            return product;
        }
    }
}
