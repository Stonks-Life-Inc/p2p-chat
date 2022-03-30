package src.controller;

import src.app._GLOBAL;
import src.network.HelloThread;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

public class ThreadController {

    static MainWindow mw;
    Connect con;
    Disconnect disconnect;

    public ThreadController(MainWindow mw, Connect con, Disconnect disconnect) {
        this.mw = mw;
        this.con = con;
        this.disconnect = disconnect;
    }

    public ThreadController() {
    }

    public ThreadController(MainWindow mw) {
        this.mw = mw;
    }

    public ThreadController(Connect con) {
        this.con = con;
    }

    public ThreadController(Disconnect disconnect) {
        this.disconnect = disconnect;
    }


    public static void updateNewMsg(String message) {
        if(!HelloThread.getUsrs().isEmpty()) {
            for(int i = 0; i < HelloThread.getUsrs().size(); i++) {
                // Get the sender of the message
                if(HelloThread.getUsrs().get(i).getIpAdress().equals(_GLOBAL.getCurrRemoteUsrIP())) {
                    try {
                        // Update the ChatSystem View
                        mw.getMsgTextArea().append("FROM [" + HelloThread.getUsrs().get(i).getName() + "] : " + message + "\n");
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                    break;
                }
            }

        }
    }

    public static void updateUserState(){
        if(_GLOBAL.isConnected()){
            mw.getSendBtn().setEnabled(false);
            mw.getMsgInputBox().setEnabled(false);
            mw.getConBtn().setText("Connect");
        }else{
            mw.getSendBtn().setEnabled(true);
            mw.getMsgInputBox().setEnabled(true);
            mw.getConBtn().setText("Disconnect");
        }

    }


    public MainWindow getMw() {
        return mw;
    }

    public void setMw(MainWindow mw) {
        this.mw = mw;
    }

    public Connect getCon() {
        return con;
    }

    public void setCon(Connect con) {
        this.con = con;
    }

    public Disconnect getDisconnect() {
        return disconnect;
    }

    public void setDisconnect(Disconnect disconnect) {
        this.disconnect = disconnect;
    }
}
