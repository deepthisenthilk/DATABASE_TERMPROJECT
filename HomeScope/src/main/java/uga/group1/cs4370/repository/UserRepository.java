package uga.group1.cs4370.repository;

import uga.group1.cs4370.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Row mapping
    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setPasswordHash(rs.getString("password_hash"));
        return user;
    };

    // Signing up
    public int createUser(String fullName, String email, String passwordHash) {
        String sql = "INSERT INTO users (full_name, email, password_hash) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, fullName, email, passwordHash);
    }

    // Logging in
    public User findByEmail(String email) {
        String sql = "SELECT user_id, full_name, email, password_hash FROM users WHERE email = ?";

        List<User> users = jdbcTemplate.query(sql, userRowMapper, email);

        return users.isEmpty() ? null : users.get(0);
    }

    // Making sure email exists
    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }
}