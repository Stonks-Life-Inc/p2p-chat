package src.app;


import com.sun.tools.javac.Main;
import src.controller.MainWindow;
import src.controller.ThreadController;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class P2PChatSystem {
    private static ThreadController tc;
    private BufferedReader input;



    public static void main(String[] args) throws IOException {
        tc = new ThreadController( new MainWindow());
        MainWindow mainWindow = tc.getMw();

    }
}