package it.szyszka.skillmarket.modules.mails.model;

/**
 * Created by rafal on 08.12.17.
 */

public class ProofOfPosting extends PreparedMessage {

    private String status;
    private String recipientEmail;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }
}
