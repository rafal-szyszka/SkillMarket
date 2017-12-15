package it.szyszka.skillmarket.modules.offers.model;

/**
 * Created by rafal on 08.12.17.
 */

public class Advertisement {

    private Long id;
    private String title;
    private String category;
    private String shortDescription;
    private String detailDescription;
    private String payment;
    private Status status;

    public enum Status {
        LIVE, OUTDATED, ARCHIVAL
    }

    public enum Character {
        CONTRACTOR, PAYMASTER
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private Advertisement(Long id, String title, String category, String shortDescription, String detailDescription, String payment, Status status) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.shortDescription = shortDescription;
        this.detailDescription = detailDescription;
        this.payment = payment;
        this.status = status;
    }

    public static class Builder {
        private Long id;
        private String title;
        private String category;
        private String shortDescription;
        private String detailDescription;
        private String payment;
        private Advertisement.Status status;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Builder setDetailDescription(String detailDescription) {
            this.detailDescription = detailDescription;
            return this;
        }

        public Builder setPayment(String payment) {
            this.payment = payment;
            return this;
        }

        public Builder setStatus(Advertisement.Status status) {
            this.status = status;
            return this;
        }

        public Advertisement build() {
            return new Advertisement(id, title, category, shortDescription, detailDescription, payment, status);
        }
    }
}
