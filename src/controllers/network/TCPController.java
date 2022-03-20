package src.controllers.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPController {

    int port;

    //Network attributes
    ServerSocket serverSocket;
    Socket clientSocket;
    DataOutputStream clientDataOutputStream;
    DataInputStream serverDataInputStream;

    public TCPController(int port) throws IOException, InterruptedException {
        this.port = port;

        // Creation of our server socket with the given port linked to this obj
        //Server side
        serverSocket = new ServerSocket(this.port);
        //Client side
        clientSocket  = new Socket("localhost", this.port);

        //Connect any new client
        clientSocket = serverSocket.accept();
        System.out.println("Client Connected");


        clientDataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        serverDataInputStream = new DataInputStream(clientSocket.getInputStream());

    }

    public void disconnect(){
        // Closing the socket.
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: implement using threads inside the main loop!
    /*public static void main(String[] args)  {
        //Server side
        while (true) {

            // Lire le msg
            String clientMessage = serverDataInputStream.readUTF();
            System.out.println("client says: " + clientMessage);

            // on quitte la boucle
            if (clientMessage.equalsIgnoreCase("exit"))
                break;
        }

        //Client side
        while (true) {
                //On affiche dans la console le stream output de notre socket
                BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(System.in));

                //Lire message
                String clientMessage = clientBufferedReader.readLine();

                // envoie du message sur le server
                clientDataOutputStream.writeUTF(clientMessage);

                // On quitte la boucle pour éviter une boucle infinie quand l'utilisateur envoie exit
                //On pourra remplacer le message exit par un bouton déconnexion
                if (clientMessage.equalsIgnoreCase("exit"))
                    break;
            }
    }*/
}
