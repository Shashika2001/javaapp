import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Main application class that starts an embedded Tomcat server on port 8080.
 *
 * Routes:
 * - GET /hello -> returns JSON: {"message":"Hello DevOps"}
 * - GET /      -> serves static content from the webapp/ folder (index.html)
 */
public class Application {

    public static void main(String[] args) throws Exception {
        // Create and configure embedded Tomcat.
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // Point Tomcat to the webapp directory for static files like index.html.
        String webAppDir = Path.of("webapp").toAbsolutePath().toString();
        Context context = tomcat.addWebapp("", webAppDir);

        // Register a simple servlet for /hello endpoint.
        HttpServlet helloServlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

                try (PrintWriter writer = resp.getWriter()) {
                    writer.write("{\"message\":\"Hello DevOps\"}");
                }
            }
        };

        Tomcat.addServlet(context, "helloServlet", helloServlet);
        context.addServletMappingDecoded("/hello", "helloServlet");

        // Start server and block main thread.
        tomcat.start();
        tomcat.getServer().await();
    }
}
