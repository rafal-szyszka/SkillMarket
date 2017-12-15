package it.szyszka.skillmarket.modules.mails.model;

import it.szyszka.skillmarket.modules.user.model.User;

/**
 * Created by rafal on 07.12.17.
 */

public class SendMail extends Mail {

    private User sender;
    private String recipientFullName;

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getRecipientFullName() {
        return recipientFullName;
    }

    public void setRecipientFullName(String recipientFullName) {
        this.recipientFullName = recipientFullName;
    }

    @Override
    public String getFullName() {
        return recipientFullName;
    }
}
