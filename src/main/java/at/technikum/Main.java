package at.technikum;

import at.technikum.server.HttpServer;

public class Main {
    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.start(10001);
    }
}