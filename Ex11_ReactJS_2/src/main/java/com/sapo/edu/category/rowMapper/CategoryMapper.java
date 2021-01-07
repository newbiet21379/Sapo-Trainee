package com.sapo.edu.category.rowMapper;

import com.sapo.edu.category.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    /**
     * Mapping giá trị từ database thành Category
     *
     * @return Category
     */
    @Override
    public Category mapRow(ResultSet rs, int i) throws SQLException {
        Category category = new Category();

        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        category.setCreate_date(rs.getString("create_date"));
        category.setFix_date(rs.getString("fix_date"));
        category.setDescription(rs.getString("description"));

        return category;
    }
}
