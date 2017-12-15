package it.szyszka.skillmarket.modules.mails.model;

/**
 * Created by rafal on 08.12.17.
 */

public class PreparedMessage {

    private Long senderID;
    private Long recipientID;
    private Message message;

    public Long getSenderID() {
        return senderID;
    }

    public void setSenderID(Long senderID) {
        this.senderID = senderID;
    }

    public Long getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(Long recipientID) {
        this.recipientID = recipientID;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
