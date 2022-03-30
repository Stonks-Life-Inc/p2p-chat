package src.entity;

import src.entity.Contact.User;

import java.io.Serializable;
import java.util.Objects;

public class Hello implements Serializable {

    private src.entity.Contact.User usr;
    private Boolean answer;

    public Hello(src.entity.Contact.User user, boolean b) {
        this.usr = user;
        this.answer = b;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }


}
