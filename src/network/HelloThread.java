package src.network;

import src.app._GLOBAL;
import src.controller.ThreadController;
import src.entity.Contact.User;
import src.entity.Hello;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Objects;

public class HelloThread extends Thread {

    private DatagramSocket socket;
    private src.entity.Contact.User currUsr;
    public static ArrayList<src.entity.Contact.User> users = new ArrayList<User>();

    public HelloThread(DatagramSocket socket, src.entity.Contact.User currUsr) throws SocketException {
        this.socket = socket;
        this.currUsr = currUsr;
    }

    public void run() {
        byte[] incomingData = new byte[1024];

        while (true) {
            // Create a UDP Packet from the incoming packet
            DatagramPacket receivePacket = new DatagramPacket(incomingData, incomingData.length);
            try {
                socket.receive(receivePacket);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            // Get the Data from the received packet
            byte[] receiveData = receivePacket.getData();

            // Deserialize the data
            ByteArrayInputStream in = new ByteArrayInputStream(receiveData);
            ObjectInputStream is;
            // Create the space memory to receive the Hello Request
            Hello helloResponse = null;
            try {
                is = new ObjectInputStream(in);
                // Get the Hello request
                helloResponse = (Hello) is.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            // Check the user who send the Hello Request
            if(!helloResponse.getUsr().equals(currUsr)) {
                System.out.println("HELLO received from : " + helloResponse.getUsr().getName() + " IP ADDR : " + helloResponse.getUsr().getIpAdress() + " Answer needed : " + helloResponse.getAnswer());
                // Check if the User is already connected
                boolean isInsideList = false;
                if(!users.isEmpty()) {
                    for(int i = 0; i < users.size(); i++) {
                        if(users.get(i).equals(helloResponse.getUsr()))
                            isInsideList = true;
                    }
                }
                // If not already connected, we add him
                if(!isInsideList)
                    users.add(helloResponse.getUsr());
            }

            // Check if an answer is needed
            if(helloResponse.getAnswer()) {
                // Create client DatagramSocket
                DatagramSocket udpClientSocket;
                try {
                    udpClientSocket = new DatagramSocket();
                    InetAddress ia = InetAddress.getByName(String.valueOf(helloResponse.getUsr().getIpAdress()));
                    udpClientSocket.connect(ia, _GLOBAL.getHelloPort());

                    // Create an Hello Request
                    Hello helloMessage = new Hello(getCurrUsr(), false);
                    // Serialize the Hello Request
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(helloMessage);

                    // Convert the Hello Request into bytes
                    byte [] sendData = bos.toByteArray();

                    // Create the UPD Packet
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ia, _GLOBAL.getHelloPort());

                    // Send the UDP packet to the sender of the previous Hello Request
                    udpClientSocket.send(sendPacket);
                }	catch(IOException e) {
                    e.printStackTrace();
                }
            }
            // The thread is not doing anything particularly important and
            // if any other threads/processes need to be run, they should run
            ThreadController.updateUserList();
            Thread.yield();
        }
    }

    public src.entity.Contact.User getCurrUsr() {
        return currUsr;
    }

    public void setCurrUsr(src.entity.Contact.User currUsr) {
        this.currUsr = currUsr;
    }

    public static ArrayList<src.entity.Contact.User> getUsrs() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelloThread that = (HelloThread) o;
        return Objects.equals(socket, that.socket) && Objects.equals(currUsr, that.currUsr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socket, currUsr);
    }
}
