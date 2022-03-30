package src.network;

import src.app.P2PChatSystem;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Set;

public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private static Set<ClientThread> clients;

    public ServerThread(int portNb) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNb);

    }

    public void run() {
        try {
            while (true) {
                ClientThread clientThread = new ClientThread(this, serverSocket.accept());
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String message) {
        try {
            clients.forEach(client -> {
                client.getPrintWriter().println(message);
                P2PChatSystem.registerMessage(message);
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<ClientThread> getClients() {
        return clients;
    }
}
