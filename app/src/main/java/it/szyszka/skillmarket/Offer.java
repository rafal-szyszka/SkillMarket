package it.szyszka.skillmarket;

/**
 * Created by rafal on 05.07.17.
 */

public class Offer {

    public static final boolean LONG_DESCRIPTION = true;
    public static final boolean SHORT_DESCRIPTION = false;

    private String owner;
    private String ownerEmail;

    private String title;
    private String category;
    private String shortDescription;
    private String longDescription;
    private String paymentDetails;


    public void attachOwner(String owner) {
        this.owner = owner;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public void writeTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void writeShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void writeLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void attachPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String showOfferOwner() {
        return owner;
    }

    public String showOfferOwnerEmail() {
        return ownerEmail;
    }

    public String showTitle() {
        return title;
    }

    public String showCategory() {
        return category;
    }

    public String showDescription(boolean showLongDescription) {
        return showLongDescription ? longDescription : shortDescription;
    }

    public String showPaymentDetails(){
        return paymentDetails;
    }
}
