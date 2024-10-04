package at.technikum.api.controller;

import java.io.BufferedWriter;
import java.io.IOException;

public class UserController {

    public static void handleRequest(String method, String path, BufferedWriter out) throws IOException {
        if (method.equals("GET")) {
            if (path.equals("/users")) {
                sendUserList(out);
            } else if (path.matches("/users/\\d+")) {
                sendUserDetails(out, path);
            } else {
                sendNotFound(out);
            }
        } else {
            sendMethodNotAllowed(out);
        }
    }

    private static void sendUserList(BufferedWriter out) throws IOException {
        out.write("HTTP/1.1 200 OK\r\n");
        out.write("Content-Type: application/json\r\n");
        out.write("\r\n");
        out.write("[{\"id\": 1, \"name\": \"John\"}, {\"id\": 2, \"name\": \"Jane\"}]");
        out.flush();
    }

    private static void sendUserDetails(BufferedWriter out, String path) throws IOException {
        String userId = path.split("/")[2];
        out.write("HTTP/1.1 200 OK\r\n");
        out.write("Content-Type: application/json\r\n");
        out.write("\r\n");
        out.write("{\"id\": " + userId + ", \"name\": \"John Doe\"}");
        out.flush();
    }

    private static void sendNotFound(BufferedWriter out) throws IOException {
        out.write("HTTP/1.1 404 Not Found\r\n");
        out.write("Content-Type: text/plain\r\n");
        out.write("\r\n");
        out.write("404 - User Not Found");
        out.flush();
    }

    private static void sendMethodNotAllowed(BufferedWriter out) throws IOException {
        out.write("HTTP/1.1 405 Method Not Allowed\r\n");
        out.write("Content-Type: text/plain\r\n");
        out.write("\r\n");
        out.write("405 - Method Not Allowed");
        out.flush();
    }
}
