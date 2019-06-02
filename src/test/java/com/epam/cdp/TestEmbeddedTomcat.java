package com.epam.cdp;

import org.apache.catalina.LifecycleException;

import javax.servlet.ServletException;

class TestEmbeddedTomcat extends EmbeddedTomcat {

    TestEmbeddedTomcat(int port) {
        super(port);
    }

    @Override
    void start() {
        try {
            deploy();
            tomcat.start();
        } catch (ServletException | LifecycleException exc) {
            throw new RuntimeException(exc);
        }
    }
}
