package src.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReader {
    public static void main(String[] args) throws SocketException {
        // DatagramPacket declaration
        DatagramPacket dpac;

        // Funny number
        DatagramSocket dsoc = new DatagramSocket(69);

        byte [] b= new byte[64];
        String data = "no data";

        try {
            // Infinite Loop, always listening to the DatagramSocket to receive packages
            while (true) {

                // Initialization of the DatagramPacket, empty by default. It will receive our packets coming from the server
                dpac = new DatagramPacket(b, b.length);

                // Receiving packages from the DatagramSocket and storing them in the DatagramPacket
                dsoc.receive(dpac);

                // Extract the data stored in DatagramPacket and assign it to the data variable
                data = new String(dpac.getData());

                // Using the data received, in this case, printing them by console.
                System.out.println("We received " + data);
            }
        } catch (IOException e) {
            // Exceptions to be handle
            System.out.println(e);
        }
        // Closing the socket.
        dsoc.close();
    }
}