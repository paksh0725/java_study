package com.seonpt.researcher.common.repository;


import com.seonpt.researcher.common.entity.SimpleUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
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

    public SimpleUser findById(Long id){
        String sql = "SELECT * FROM SIMPLE_USERS WHERE ID=?";

        return jdbcTemplate.queryForObject(sql, simpleUserRowMapper, id);
    }

    public void update(long id, String name){
        String sql = "UPDATE SIMPLE_USERS SET name=? WHERE id = ?";

        jdbcTemplate.update(sql, name, id);
    }

    public void delete(long id){
        String sql = "DELETE FROM SIMPLE_USERS WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteAll(){
        String sql = "DELETE FROM SIMPLE_USERS";
        jdbcTemplate.update(sql);
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
