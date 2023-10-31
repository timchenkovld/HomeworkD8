package org.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'");
        String formattedTime = currentTime.format(formatter);

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Поточний час по UTC</title></head>");
        out.println("<body>");
        out.println("<h1>Поточний час по UTC</h1>");
        out.println("<p>" + formattedTime + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
