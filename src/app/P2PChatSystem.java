package src.app;

import src.controller.MainWindow;
import src.network.ClientThread;
import src.network.ListenerThread;
import src.network.ServerThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class P2PChatSystem {

    private static boolean isConnected;
    private static int port;
    private static String username;
    private static MainWindow mainwindow;
    private BufferedReader input;
    private ServerThread server;


    public static void main(String[] args) throws IOException {
        mainwindow =  new MainWindow();

        while (true) {
            //Sleep for a second
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println("Sleep failed");
            }
            if(isConnected()) {
                System.out.println("Connected! Enabling chat system...");
                mainwindow.getSendBtn().setEnabled(true);
                mainwindow.getMsgInputBox().setEnabled(true);

                ServerThread server = new ServerThread(port);
                server.start();
                System.out.println("Server started");
                try {
                    new P2PChatSystem().updateListener();
                } catch (Exception e) {
                    e.printStackTrace();
                    isConnected = false;
                    mainwindow.getSendBtn().setEnabled(false);
                    mainwindow.getMsgInputBox().setEnabled(false);
                }
            }
        }
    }


    public void updateListener() throws Exception{
        System.out.println("Update Listener");
        input = new BufferedReader(new java.io.InputStreamReader(System.in));

        Socket socket = null;
        try{
            socket = new Socket(getUsername(), getPort());
            new ListenerThread(socket).start();
        }catch (Exception e){
            if (socket != null) { socket.close(); }
            else { System.out.println("Socket is null"); }
        }

        /*port = Integer.parseInt(input.readLine());
        System.out.println("Enter a username: ");
        username = input.readLine();
        setConnected(true);*/
    }

    public static boolean isConnected() {
        return isConnected;
    }

    public static void setConnected(boolean connected) {
        isConnected = connected;
        System.out.println("Connected: " + isConnected);
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int portArg) {
        port = portArg;
        System.out.println("Port: " + port);
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String usernameArg) {
        username = usernameArg;
        System.out.println("Username: " + username);
    }

    public static void registerMessage(String msg) {
        mainwindow.getMsgTextArea().append(msg);
    }
}
