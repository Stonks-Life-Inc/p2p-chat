package src.network;

import src.entity.Bye;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class ByeThread extends Thread {
    private DatagramSocket clientSocket;
    private src.entity.Contact.User currUser;
    public static ArrayList<src.entity.Contact.User> users;

    public ByeThread(DatagramSocket clientSocket, src.entity.Contact.User currUser) {
        this.clientSocket = clientSocket;
        this.currUser = currUser;
    }

    public void run(){
        byte[] incomingData = new byte[1024];
        boolean isInsideList;

        while (true) {
            // Create a UDP Packet from the incoming packet
            DatagramPacket receivePacket = new DatagramPacket(incomingData, incomingData.length);
            try {
                clientSocket.receive(receivePacket);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            // Get the Data from the received packet
            byte[] receiveData = receivePacket.getData();

            // Deserialize the data
            ByteArrayInputStream in = new ByteArrayInputStream(receiveData);
            ObjectInputStream is;

            // Create the space memory to receive the Bye Request
            Bye byeResponse = null;
            try {
                is = new ObjectInputStream(in);
                // Get the Bye request
                byeResponse = (Bye) is.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            isInsideList = false;
            // Check if the User was connected before his disconnection
            if(!HelloThread.getUsrs().isEmpty()) {
                for(int i = 0; i < HelloThread.getUsrs().size(); i++) {
                    if(HelloThread.getUsrs().get(i).equals(byeResponse.getUsr()))
                        isInsideList = true;
                }
            }
            // If the user was connected and now disconnected, we add this User to the ArrayList
            if(isInsideList) {
                HelloThread.users.remove(byeResponse.getUsr());
            }
            // The thread is not doing anything particularly important and
            // if any other threads/processes need to be run, they should run
            Thread.yield();
        }
    }

    public DatagramSocket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(DatagramSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public src.entity.Contact.User getCurrUser() {
        return currUser;
    }

    public void setCurrUser(src.entity.Contact.User currUser) {
        this.currUser = currUser;
    }

    public static ArrayList<src.entity.Contact.User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<src.entity.Contact.User> users) {
        ByeThread.users = users;
    }
}
