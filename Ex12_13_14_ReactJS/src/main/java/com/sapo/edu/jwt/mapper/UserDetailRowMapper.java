package com.sapo.edu.jwt.mapper;


import com.sapo.edu.jwt.model.Role;
import com.sapo.edu.jwt.model.RoleName;
import com.sapo.edu.jwt.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserDetailRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getInt(1));
        user.setUsername(rs.getString(2));
        user.setPassword(rs.getString(3));
        Set<Role> roleSet=new HashSet<>();
        Role role=new Role();
        role.setId(rs.getInt(5));
        role.setName(RoleName.valueOf(rs.getString(4)));
        roleSet.add( role);
        user.setRoles(roleSet);

        return user;
    }
}
