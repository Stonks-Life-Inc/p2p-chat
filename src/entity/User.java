package src.entity.Contact;


import src.app._GLOBAL;
import src.entity.Bye;
import src.entity.Hello;
import src.network.ByeThread;
import src.network.HelloThread;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;
import java.util.Objects;


public class User implements Serializable {
    private String name;
    private String ipAdress;
    

    public User(String name) {
        this.name = name;
        setIpAdress();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress() {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("7.7.7.7"), _GLOBAL.getMsgPort());
            this.ipAdress = (String) socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", ipAdress=" + ipAdress +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        src.entity.Contact.User user = (src.entity.Contact.User) o;
        return ipAdress == user.ipAdress && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ipAdress);
    }

    //Each user can send a hello or bye request to ping each user on the network to tell they are online
    public void hello(InetAddress address, int remotePort) throws IOException {
        // Create client DatagramSocket
        DatagramSocket udpClientSocket;
        try {
            // Create and Start the HelloThread
            HelloThread helloThread = new HelloThread(new DatagramSocket(remotePort), this);
            helloThread.start();
            // Create and Start the ByeThread
            ByeThread byeThread = new ByeThread(new DatagramSocket(_GLOBAL.getGoodbyePort()), this);
            byeThread.start();

            udpClientSocket = new DatagramSocket();
            udpClientSocket.setBroadcast(true);
            udpClientSocket.connect(address, remotePort);

            // Create an Hello Request
            Hello helloMessage = new Hello(this, true);
            // Serialize the Hello Request
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(helloMessage);

            // Convert the Hello Request into bytes
            byte [] sendData = bos.toByteArray();

            // Create the UPD Packet
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, remotePort);

            // Send the UDP packet to the broadcast address
            udpClientSocket.send(sendPacket);

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void bye() throws IOException {
        // Create client DatagramSocket
        DatagramSocket udpClientSocket;
        try {
            udpClientSocket = new DatagramSocket();

            // Create an Bye Request
            Bye byeMessage = new Bye(this);
            // Serialize the Bye Request
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(byeMessage);

            // Convert the Bye Request into bytes
            byte [] sendData = bos.toByteArray();

            InetAddress currentIpAddress = InetAddress.getByName("192.168.1.255");
            udpClientSocket.setBroadcast(true);
            udpClientSocket.connect(currentIpAddress, _GLOBAL.getGoodbyePort());

            // Create the UPD Packet
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, currentIpAddress, _GLOBAL.getGoodbyePort());

            // Send the UDP packet to the broadcast address
            udpClientSocket.send(sendPacket);

        }catch (SocketException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
