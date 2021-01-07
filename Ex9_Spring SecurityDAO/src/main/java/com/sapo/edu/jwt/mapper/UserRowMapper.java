package com.sapo.edu.jwt.mapper;

import com.sapo.edu.jwt.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getInt(1));
        user.setUsername(rs.getString(2));
        user.setPassword(rs.getString(3));

        return user;
    }
}
