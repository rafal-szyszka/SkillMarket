package it.szyszka.skillmarket.modules.mails.model;

/**
 * Created by rafal on 07.12.17.
 */

public abstract class Mail {

    protected Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public abstract String getFullName();
}
