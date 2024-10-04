package at.technikum.api.controller;

import java.io.BufferedWriter;
import java.io.IOException;

public class SessionController {

    public static void handleRequest(String method, String path, BufferedWriter out) throws IOException {
        if (method.equals("GET") && path.equals("/sessions")) {
            sendSessionList(out);
        } else {
            sendNotFound(out);
        }
    }

    private static void sendSessionList(BufferedWriter out) throws IOException {
        out.write("HTTP/1.1 200 OK\r\n");
        out.write("Content-Type: application/json\r\n");
        out.write("\r\n");
        out.write("[{\"sessionId\": \"abc123\", \"userId\": 1}, {\"sessionId\": \"xyz789\", \"userId\": 2}]");
        out.flush();
    }

    private static void sendNotFound(BufferedWriter out) throws IOException {
        out.write("HTTP/1.1 404 Not Found\r\n");
        out.write("Content-Type: text/plain\r\n");
        out.write("\r\n");
        out.write("404 - Session Not Found");
        out.flush();
    }
}
