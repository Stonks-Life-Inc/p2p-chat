package src.network;

import src.app._GLOBAL;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SenderThread extends Thread {

    private InetAddress remoteIp;
    private DatagramSocket socket;
    private String msg;

    public SenderThread(InetAddress remoteIp, int msgPort) throws SocketException {
        this.remoteIp = remoteIp;

        this.socket = new DatagramSocket();
        this.socket.setBroadcast(true);
        this.socket.connect(remoteIp, _GLOBAL.getMsgPort());
    }

    public void run() {
        try {
            while (true) {
                System.out.println("Sending message to " + remoteIp.getHostAddress() + ":" + _GLOBAL.getMsgPort());
                if(msg != "") {
                    byte[] data = msg.getBytes();

                    DatagramPacket packet = new DatagramPacket(data, data.length, remoteIp, _GLOBAL.getMsgPort());
                    socket.send(packet);
                    msg = "";
                }
                //Thread.interrupted();
                Thread.yield();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InetAddress getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(InetAddress remoteIp) {
        this.remoteIp = remoteIp;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
