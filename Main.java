import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.OutputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(
            new InetSocketAddress(8080), 0
        );

        server.createContext("/", exchange -> {

            String response =
                "Hello from Java Kubernetes Application!\n" +
                "Jenkins CI/CD Pipeline Working!\n" +
                "Application deployed successfully!";

            exchange.sendResponseHeaders(200, response.length());

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();

        System.out.println("Java HTTP Server started on port 8080");
    }
}
