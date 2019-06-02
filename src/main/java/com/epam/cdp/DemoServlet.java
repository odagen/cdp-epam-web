package com.epam.cdp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/demo")
public class DemoServlet extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html";
    private static final String CONTENT_HEADER = "<html><body>";
    private static final String CONTENT_BODY = "<h1>Hello from servlet<h1>";
    private static final String CONTENT_TAIL = "</body></html>";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String content = CONTENT_HEADER + CONTENT_BODY + CONTENT_TAIL;

        response.setContentType(CONTENT_TYPE);
        try (PrintWriter writer = response.getWriter()) {
            writer.write(content.toCharArray());
            writer.flush();
        }
    }
}
