package network.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {

        //Déclaration du socket en localhost
        Socket clientSocket = new Socket("localhost", 69);


        DataOutputStream clientDataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        //On affiche dans la console le stream output de notre socket
        BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {

                //Lire message
                String clientMessage = clientBufferedReader.readLine();

                // envoie du message sur le server
                clientDataOutputStream.writeUTF(clientMessage);

                // On quitte la boucle pour éviter une boucle infinie quand l'utilisateur envoie exit
                //On pourra remplacer le message exit par un bouton déconnexion
                if (clientMessage.equalsIgnoreCase("exit"))
                    break;
            }

        } finally {
            // Closing the socket.
            clientSocket.close();
        }
    }
}