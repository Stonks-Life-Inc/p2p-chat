package src.controller;

import src.app.P2PChatSystem;
import src.app._GLOBAL;
import src.entity.Contact.User;
import src.network.RecieverThread;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Connect extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    //private JSpinner spinner1;

    public Connect() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Display the window.
        pack();
        setVisible(true);
    }

    private void onOK() throws IOException {

        // We set our global vars
        _GLOBAL.setUsername(textField1.getText());
        _GLOBAL.setConnected(true);


        // Init the hello class and launch a hello broadcast message
        // Retrieve the list of user who have responded to the broadcast
        // Comunicate this list to the main to add each user to the list of connected user
        User usr = new User(textField1.getText());
        _GLOBAL.setLocalUser(usr);

        InetAddress ip = InetAddress.getByName("192.168.1.255");
        usr.hello(ip, _GLOBAL.getHelloPort());

        // We need to create the reciever to register the broadcast responses from other distant user
        RecieverThread reciever = new RecieverThread(new DatagramSocket(_GLOBAL.getMsgPort()));
        _GLOBAL.setRecieverThread(reciever);
        reciever.start();
        ThreadController.updateUserState();

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
