package src.controller;

import src.app.P2PChatSystem;
import src.app._GLOBAL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
        setSize(750,800);
        setMinimumSize(getSize());

        // Connection button listener
        conBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Do a test to see if user is connected to the network or not
                // If not, we want to display a new instance of the connection form window
                // Else we want to display a new instance of the disconnection form window
                if (!_GLOBAL.isConnected()) {
                    new Connect();
                } else {
                    new Disconnect();
                }

            }
        });
        //Listen for enter key press on the input box
        //if enter key is pressed, send the message
        msgInputBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    super.keyPressed(e);
                    sendBtn.doClick(); //call the send button action listener to save lines of code
                }

            }
        });

        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calling the send message method from ServerThread class

                msgInputBox.setText("");
                msgTextArea.append("You: " + msgInputBox.getText() + "\n");
            }
        });
    }

    public JTextField getMsgInputBox() {
        return msgInputBox;
    }

    public void setMsgInputBox(JTextField msgInputBoxArg) {
        msgInputBox = msgInputBoxArg;
    }

    public JButton getSendBtn() {
        return sendBtn;
    }

    public void setSendBtn(JButton sendBtnArg) {
        sendBtn = sendBtnArg;
    }

    public JTextArea getMsgTextArea() {
        return msgTextArea;
    }

    public void setMsgTextArea(JTextArea msgTextArea) {
        this.msgTextArea = msgTextArea;
    }

    public JButton getConBtn() {
        return conBtn;
    }

    public void setConBtn(JButton conBtn) {
        this.conBtn = conBtn;
    }

    // Method to update the friend list
    public void updateConUserList(ArrayList<src.entity.Contact.User> userList) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (src.entity.Contact.User user : userList) {
            listModel.addElement(user.getName());
        }
        friendList.setModel(listModel);
    }
}
