package src.controller;

import src.app.P2PChatSystem;

import javax.swing.*;
import java.awt.event.*;

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
                onOK();
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

    private void onOK() {


        //P2PChatSystem.setPort(Integer.parseInt(spinner1.getValue().toString()));

        P2PChatSystem.setUsername(textField1.getText());
        P2PChatSystem.setConnected(true);


        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
