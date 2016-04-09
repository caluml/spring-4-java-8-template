package com.example;

import com.example.dao.Dao;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class TestSpringAutowiring {

    @Test
    public void SpringAutowiresCorrectly() {
        AbstractXmlApplicationContext context = new FileSystemXmlApplicationContext();
        context.setConfigLocation("/src/main/webapp/WEB-INF/applicationContext.xml");
        context.refresh();

        assertNotNull("Can't get Dao", context.getBean(Dao.class));
    }
}
