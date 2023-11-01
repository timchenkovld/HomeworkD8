package org.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");

        String encodedTimezone = req.getParameter("timezone");
        ZoneId zoneId;
        if (encodedTimezone !=null && !encodedTimezone.isEmpty()){
            if (encodedTimezone.contains(" ")){
                String modifiedTimezone = encodedTimezone.replace(" ", "%2B");
                String timezone = URLDecoder.decode(modifiedTimezone, StandardCharsets.UTF_8);
                zoneId = ZoneId.of(timezone);
            } else {
                zoneId = ZoneId.of(encodedTimezone);
            }
        } else {
            zoneId = ZoneId.of("UTC");
        }

        ZonedDateTime currentTime = ZonedDateTime.now(zoneId);
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