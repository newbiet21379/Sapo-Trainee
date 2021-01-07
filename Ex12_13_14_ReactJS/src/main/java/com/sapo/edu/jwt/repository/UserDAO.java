package com.sapo.edu.jwt.repository;

import com.sapo.edu.jwt.mapper.RoleRowMapper;
import com.sapo.edu.jwt.mapper.UserDetailRowMapper;
import com.sapo.edu.jwt.mapper.UserRowMapper;
import com.sapo.edu.jwt.model.Role;
import com.sapo.edu.jwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserDAO implements UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findByUsername(String username) {

        String sqlRole="select p.id,p.name_per from user u \n" +
                "                inner join user_per up on u.id=up.id_user \n" +
                "                inner join permission p on up.id_per=p.id \n" +
                "                where u.username=?";

        String sql="select  u.id,u.username,u.password,p.name_per,p.id from user u \n" +
                "                inner join user_per up on u.id=up.id_user \n" +
                "                inner join permission p on up.id_per=p.id \n" +
                "                where u.username=? limit 0,1   ";
        List<Role> roleList=jdbcTemplate.query(sqlRole,new RoleRowMapper(),username);
        Set<Role> roles=new HashSet<>(roleList);
        User user=jdbcTemplate.queryForObject(
                sql, new UserDetailRowMapper(), username);
        user.setRoles(roles);
        return Optional.ofNullable(user);
    }

    @Override
    public Boolean existsByUsername(String username) {
        List<User> users = getAllUser();
        for (User user : users
        ) {
            if (user.getUsername().equalsIgnoreCase(username))
                return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUser() {
        return jdbcTemplate.query("select * from user ", new UserRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(User user) {

        jdbcTemplate.update("INSERT into USER(username,password) VALUES (?,?)", user.getUsername(), user.getPassword());

        Role role = user.getRoles().stream().findFirst().get();
        System.out.println(role.getName() + " " + findLastUser().getId() + " " + role.getId()+" "+user.getUsername());

        jdbcTemplate.update("INSERT into user_per(id_user,id_per,licensed) VALUES(?,?,?)", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1,findLastUser().getId());
                ps.setInt(2,role.getId());
                ps.setInt(3,1);
            }
        });
    }

    @Override
    public User findLastUser() {
        return jdbcTemplate.queryForObject("select * from user order by id desc limit 1", new UserRowMapper());
    }
}
