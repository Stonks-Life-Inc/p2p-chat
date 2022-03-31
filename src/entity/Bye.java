package src.entity;

import src.entity.Contact.User;

import java.io.Serializable;

public class Bye implements Serializable {

    private static final long serialVersionUID = 244800800274812133L;

    private src.entity.Contact.User usr;

    public Bye(User usr) {
        this.usr = usr;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }


}
