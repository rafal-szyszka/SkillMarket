package it.szyszka.skillmarket.modules.mails.model;

import java.util.List;

/**
 * Created by rafal on 07.12.17.
 */

public class Mailbox {

    private List<ReceivedMail> received;
    private List<SendMail> send;

    public List<ReceivedMail> getReceived() {
        return received;
    }

    public void setReceived(List<ReceivedMail> received) {
        this.received = received;
    }

    public List<SendMail> getSend() {
        return send;
    }

    public void setSend(List<SendMail> send) {
        this.send = send;
    }
}
