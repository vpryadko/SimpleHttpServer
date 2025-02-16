package org.vpriadko;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "<html>" + new java.util.Date() + "</br></br>";
            File folder = new File("C:\\");
            response = response + "Files in " + folder + "</br></br>";
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    response = response + file.getName() + "</br>";
                }
            }
            response = response + "</html>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}