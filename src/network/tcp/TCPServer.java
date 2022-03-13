package network.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Création du socket (côté serveru)
        ServerSocket serverSocket = new ServerSocket(69);

        // Il accepte toutes les requêtes
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client Connected");

        // Permet de lire le stream
        DataInputStream serverDataInputStream = new DataInputStream(clientSocket.getInputStream());

        try {

            while (true) {
                // Lire le msg
                String clientMessage = serverDataInputStream.readUTF();
                System.out.println("client says: " + clientMessage);

                // on quitte la boucle
                if (clientMessage.equalsIgnoreCase("exit"))
                    break;
            }

        } catch (IOException e) {
            // Exceptions to be handle
            System.out.println(e);
        } finally {
            // Closing the socket.
            serverSocket.close();
        }
    }
}
