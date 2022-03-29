package src.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListenerThread extends Thread {
    private BufferedReader in;

    //Constructor
    //We create a new buffered reader for the socket
    public ListenerThread(Socket socket) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    //Listening for incoming messages
    public void run() {
        boolean running = true;
        while (running) {
            try {
                while (true) {
                    String msg = in.readLine();
                    if (msg == null) {
                        break;
                    }
                    System.out.println(msg);
                }
            } catch (IOException e) {
                running = false;
                e.printStackTrace();
                interrupt();
            }
        }
    }
}
