package com.epam.cdp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/greeting")
public class GreetingServlet extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html";
    private static final String CONTENT_HEADER = "<html><body>";
    private static final String ADDITIONAL_CONTENT_BODY = "<br /> <h1>Hello, %s</h1>";
    private static final String CONTENT_TAIL = "</body></html>";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");

        String content;
        if (name != null && name.length() > 0) {
            content = String.format(ADDITIONAL_CONTENT_BODY, name);
        } else {
            content = String.format(ADDITIONAL_CONTENT_BODY, "anonymous");
        }

        content = CONTENT_HEADER + content + CONTENT_TAIL;

        response.setContentType(CONTENT_TYPE);
        try (PrintWriter writer = response.getWriter()) {
            writer.write(content.toCharArray());
            writer.flush();
        }
    }
}
