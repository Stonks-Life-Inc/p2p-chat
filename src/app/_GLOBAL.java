package src.app;

import src.controller.MainWindow;

public class _GLOBAL {
    // Set by default.
    // Can only be changed directly from code.
    // This is not secure but just a confort to finish this project
    private static int msgPort = 5555;
    private static int helloPort = 5556;
    private static int goodbyePort = 5557;
    private static int clientPort = 5558;

    // Some information  about the current user
    private static boolean isConnected;
    private static int port;
    private static String username;
    private static MainWindow mainwindow;

    // Current information about the distance user which we are connected with.
    private static String currRemoteUsrIP;

    public static int getMsgPort() {
        return msgPort;
    }

    public static int getHelloPort() {
        return helloPort;
    }

    public static int getGoodbyePort() {
        return goodbyePort;
    }

    public static int getClientPort() {
        return clientPort;
    }

    // Getters & Setters for the local user variables
    public static boolean isConnected() {
        return isConnected;
    }

    public static void setConnected(boolean connected) {
        isConnected = connected;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        _GLOBAL.port = port;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        _GLOBAL.username = username;
    }

    public static MainWindow getMainwindow() {
        return mainwindow;
    }

    public static void setMainwindow(MainWindow mainwindow) {
        _GLOBAL.mainwindow = mainwindow;
    }

    // Remote user variables
    public static String getCurrRemoteUsrIP() {
        return currRemoteUsrIP;
    }

    public static void setCurrRemoteUsrIP(String currRemoteUsrIPArg) {
        currRemoteUsrIP = currRemoteUsrIPArg;
    }
}
