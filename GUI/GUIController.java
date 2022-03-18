package GUI;

import java.util.ArrayList;

public class GUIController {
    ArrayList<GUI> guis = new ArrayList<>();
    boolean isConnected;

    public GUIController(MainWindow mainWindowGUI){
        guis.add(mainWindowGUI);
        mainWindowGUI.pack();
        mainWindowGUI.setVisible(true);
    }

    public GUIController(ConnectionCredentialsForm ccf){
        guis.add(ccf);
        ccf.pack();
        ccf.setVisible(false);
    }

    public GUIController(DisconnectConfirmation dc){
        guis.add(dc);
        dc.pack();
        dc.setVisible(false);
    }

    public void addNewGUI(Object gui, boolean isMain){
        gui = new Object();
        gui.pack();
        if(isMain) gui.setVisible(false);
    }

    public displayToggleConnectDisconnect(boolean isConn){
        if(isConn){
            disconnectDialog.setVisible(true);
        }else{
            connectDialog.setVisible(true);
        }
    }

    public void endSystemExit(){
        System.exit(0);
    }
}
