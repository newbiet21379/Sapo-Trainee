package com.sapo.edu.jwt.mapper;

import com.sapo.edu.jwt.model.Role;
import com.sapo.edu.jwt.model.RoleName;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();

        role.setId(rs.getInt(1));
        role.setName(RoleName.valueOf(rs.getString(2)));

        return role;
    }
}
