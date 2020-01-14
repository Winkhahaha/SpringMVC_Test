package test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(){
        String sql = "insert into user (username,password) values(?,?)";
        jdbcTemplate.update(sql, UUID.randomUUID().toString().substring(0,5),1999);

    }
}
