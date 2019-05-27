package com.final_project.repositories;

import com.final_project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUserByEmail(String email) {
        String sqlQuery = "select * from users where email = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, email);

        return generateUser(rs);
    }

    public int addUser(User user) {
        String sqlQuery = "INSERT INTO users(first_name, last_name, email, password, phone_number, role, active) VALUES(?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sqlQuery, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getRole(), user.isActive());
    }

    private User generateUser(SqlRowSet rs) {
        User user = new User();

        while(rs.next()) {
            user.setId(rs.getInt("user_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setRole(rs.getString("role"));
            user.setActive(rs.getBoolean("active"));

        }

        return user;
    }

}
