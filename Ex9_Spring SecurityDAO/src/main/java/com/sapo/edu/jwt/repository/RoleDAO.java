package com.sapo.edu.jwt.repository;

import com.sapo.edu.jwt.mapper.RoleRowMapper;
import com.sapo.edu.jwt.model.Role;
import com.sapo.edu.jwt.model.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class RoleDAO implements RoleRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Optional<Role> findByName(RoleName roleName) {
        System.out.println(roleName.toString());
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "select * from permission where name_per=?",new RoleRowMapper(),roleName.toString()));
    }
}
