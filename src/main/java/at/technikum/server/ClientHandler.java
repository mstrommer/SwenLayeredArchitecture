package at.technikum.server;

import at.technikum.api.controller.SessionController;
import at.technikum.api.controller.UserController;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            // Erste Zeile der HTTP-Anfrage lesen
            String line = in.readLine();
            if (line != null) {
                String[] requestParts = line.split(" ");
                String method = requestParts[0];
                String path = requestParts[1];

                // Routing basierend auf dem Pfad
                if (path.startsWith("/users")) {
                    UserController.handleRequest(method, path, out);
                } else if (path.startsWith("/sessions")) {
                    SessionController.handleRequest(method, path, out);
                } else {
                    sendNotFound(out);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendNotFound(BufferedWriter out) throws IOException {
        out.write("HTTP/1.1 404 Not Found\r\n");
        out.write("Content-Type: text/plain\r\n");
        out.write("\r\n");
        out.write("404 - Not Found");
        out.flush();
    }
}
