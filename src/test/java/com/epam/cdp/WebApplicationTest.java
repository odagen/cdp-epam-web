package com.epam.cdp;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WebApplicationTest {

    private static final TestEmbeddedTomcat TOMCAT = new TestEmbeddedTomcat(9999);

    @BeforeClass
    public static void setUp() {
        TOMCAT.start();
    }

    @AfterClass
    public static void tearDown() {
        TOMCAT.stop();
    }

    @Test
    public void shouldGetIndexPage() {
        String content = fetchContent("http://localhost:9999");
        assertNotNull(content);
        assertTrue(content.contains("Tomcat works!"));
    }

    @Test
    public void shouldGetDemoPage() {
        String content = fetchContent("http://localhost:9999/demo");
        assertNotNull(content);
        assertTrue(content.contains("Hello from servlet"));
    }

    @Test
    public void shouldGetGreetingPage() {
        String content = fetchContent("http://localhost:9999/greeting?name=UserName");
        assertNotNull(content);
        assertTrue(content.contains("Hello, UserName"));
    }

    private String fetchContent(String uri) {
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    return reader.lines().collect(Collectors.joining());
                }
            }
        } catch (IOException urlException) {
            throw new RuntimeException(urlException);
        }

        return null;
    }

}
