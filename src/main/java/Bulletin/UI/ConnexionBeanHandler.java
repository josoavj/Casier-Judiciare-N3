package Bulletin.UI;

import Bulletin.persistence.Admin.Admin;

import java.awt.*;

public class ConnexionBeanHandler {
    private static ConnexionBeanHandler instance = null;
    private static Admin Login = null;

    public static ConnexionBeanHandler getInstance(){
        if (instance == null){
            instance = new ConnexionBeanHandler();
        }
        return  instance;
    }

    public static Admin getLogin() {
        return Login;
    }

    public static void setLogin(Admin connecteAdmin) {
        ConnexionBeanHandler.Login = connecteAdmin;
    }
    public static void disconnect(){
        Frame[] frames = Frame.getFrames();
        AjoutPersonne.listeDeCondamnations.clear();
        AjoutPersonne.listCondamnationAdded.clear();
        AjoutPersonne.listCondamnationWillRemoved.clear();
        ConnexionBeanHandler.setLogin(null);
        for(Frame frame : frames){
            if(frame != BarreMenu.getInstance()){
                frame.dispose();
            }
        }
        new Connexion().setVisible(true);
    }
}
