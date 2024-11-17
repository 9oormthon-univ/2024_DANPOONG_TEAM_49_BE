package com.goormthon3.team49.domain.test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JdbcTestController {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/test-db")
    public String testDatabase() {
        try {
            String result = jdbcTemplate.queryForObject("SELECT VERSION()", String.class);
            return "Connected to MySQL, Version: " + result;
        } catch (Exception e) {
            return "Failed to connect to database: " + e.getMessage();
        }
    }
}

