package src.network;

import src.controller.MainWindow;
import src.controller.ThreadController;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class RecieverThread extends Thread {
    private DatagramSocket socket;
    private String msg="";

    public RecieverThread(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("RecieverThread started");
        byte[] buffer = new byte[1024];
        ThreadController tc = new ThreadController();
        while(true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
                msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Recieved: " + msg);

                ThreadController.updateNewMsg(msg);
                //ThreadController.updateUserList();

                Thread.yield();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
