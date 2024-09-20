package at.technikum.server;

import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // Anfrage lesen, Request verarbeiten und Antwort zurücksenden
    }
}
