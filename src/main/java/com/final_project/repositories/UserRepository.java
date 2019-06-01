package com.final_project.repositories;

import com.final_project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                ps.setString(5, user.getPhoneNumber());
                ps.setString(5, user.getRole());
                ps.setBoolean(5, user.isActive());

                return ps;
            }
        };

        return jdbcTemplate.update(psc);
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
