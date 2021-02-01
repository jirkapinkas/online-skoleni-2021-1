package com.test.helloworld.repository;

import com.test.helloworld.annotation.JdbcProfile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@JdbcProfile
@Repository
public class JdbcItemRepository implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("select count(*) from item", Long.class);
    }
}
