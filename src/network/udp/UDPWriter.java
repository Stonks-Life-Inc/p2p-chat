package network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Date;

public class UDPWriter {
    public static void main(String[] args) throws SocketException, InterruptedException {

        // DatagramPacket declaration
        DatagramPacket dpac;

        // DatagramSocket declaration
        DatagramSocket dsoc = new DatagramSocket();

        try {

            // Infinite Loop, always sending packages through the DatagramSocket
            while (true) {
                // Pause of one second of the server, to send our packages, every second.
                Thread.sleep(1000);

                // Preparation of the message to send, in this case, a simple date
                String time = new Date(0).toString();
                // Decomposition of the string variable time in their corresponding bytes.
                byte b[]=time.getBytes();

                // Funny number
                dpac = new DatagramPacket(b,b.length,InetAddress.getByName("localhost"), 69);

                // The packet is send though the socket.
                dsoc.send(dpac);
            }
        }  catch (IOException | InterruptedException e) {
            // Exceptions to be handle
            System.out.println(e);
        }
        // Closing the socket.
        dsoc.close();
    }
}
