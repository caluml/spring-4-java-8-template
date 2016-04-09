package com.example.rest;

import com.example.dao.Dao;
import com.example.MetricUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

@Component
public class Resource implements Api {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Dao dao;
    private final MetricUtils metrics;

    @Autowired
    public Resource(Dao dao, MetricUtils metrics) {
        this.dao = dao;
        this.metrics = metrics;
        logger.info("Instantiated {}", this);
    }

    @Override
    public Response test() {
        return Response.ok().build();
    }
}
