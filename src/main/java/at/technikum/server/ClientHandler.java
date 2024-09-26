package at.technikum.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private BufferedReader in;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // Anfrage lesen, Request verarbeiten und Antwort zurücksenden
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null && !inputLine.isEmpty()){
                builder.append(inputLine).append(System.lineSeparator());
            }
            // TODO process properly
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
