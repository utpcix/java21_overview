package pe.edu.utp.features.http;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class UtpServer {

    private static HttpServer httpServer;

    public static void main(String[] args) throws IOException, InterruptedException {

        start_http_server(8080);

    }

    public static void start_http_server(int port) throws IOException, InterruptedException {
        httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        httpServer.createContext("/", new HandlerUtpServer() );
        // Manage connections through Virtual Threads
        httpServer.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        httpServer.start();

        System.out.printf("http://%s:%d%n",
                httpServer.getAddress().getHostString(),
                httpServer.getAddress().getPort());
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void stop_http_server(){
        httpServer.stop(10);
    }

}
