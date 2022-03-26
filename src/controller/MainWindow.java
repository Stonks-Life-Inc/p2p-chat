package src.controller;

import src.app.P2PChatSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JButton conBtn;
    private JPanel friendListPan;
    private JList friendList;
    private JTextField msgInputBox;
    private JButton sendBtn;
    private JPanel msgTAPanel;
    private JTextArea msgTextArea;

    public MainWindow() {
        super("Chat");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        // Connection button listener
        conBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Do a test to see if user is connected to the network or not
                // If not, we want to display a new instance of the connection form window
                // Else we want to display a new instance of the disconnection form window
                if (true) {
                    new Connect();
                } else {
                    new Disconnect();
                }
                System.out.println("Connection button clicked");
            }
        });
    }
}
