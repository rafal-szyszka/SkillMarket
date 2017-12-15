package it.szyszka.skillmarket.modules.mails.model;

import it.szyszka.skillmarket.modules.user.model.User;

/**
 * Created by rafal on 07.12.17.
 */

public class ReceivedMail extends Mail {

    private User recipient;
    private String senderFullName;

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public void setSenderFullName(String senderFullName) {
        this.senderFullName = senderFullName;
    }

    @Override
    public String getFullName() {
        return senderFullName;
    }
}
