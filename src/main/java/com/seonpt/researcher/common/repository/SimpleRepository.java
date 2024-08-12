package com.seonpt.researcher.common.repository;


import com.seonpt.researcher.common.entity.SimpleUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SimpleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleUserRowMapper simpleUserRowMapper = new SimpleUserRowMapper();
    public SimpleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(SimpleUser simpleUser){
        String sql = "INSERT INTO  SIMPLE_USERS(name) VALUES (?)";

        jdbcTemplate.update(sql, simpleUser.name);
    }

    public List<SimpleUser> findAll(){
        String sql = "SELECT * FROM SIMPLE_USERS";
        return jdbcTemplate.query(sql, simpleUserRowMapper);
    }


    private class SimpleUserRowMapper implements RowMapper<SimpleUser>{
        @Override
        public SimpleUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");

            return new SimpleUser(id, name);
        }
    }

}
