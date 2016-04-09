package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcDao extends JdbcDaoSupport implements Dao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcDao(@Qualifier("jndiDatasource") DataSource dataSource) {
        super.setDataSource(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("table");
    }

}