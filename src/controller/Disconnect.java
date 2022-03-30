package src.controller;

import src.app.P2PChatSystem;
import src.app._GLOBAL;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Disconnect extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public Disconnect() {
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

        //Display the dialog
        pack();
        setVisible(true);
    }

    private void onOK() throws IOException {
        // add your code here
        _GLOBAL.setConnected(false);

        //We disconnect
        _GLOBAL.getLocalUser().bye();

        //  We update our user state in the Thread Controller.
        // This will change our view accordingly.
        ThreadController.updateUserState();

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
