package com.example;

import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.stereotype.Component;

@Component("jndiDatasource")
public class MyDatasource extends PGPoolingDataSource {

    public MyDatasource() {
        setServerName("server");
        setDatabaseName("database");
        setUser("user");
        setPassword("password");
        setInitialConnections(4);
    }

}
