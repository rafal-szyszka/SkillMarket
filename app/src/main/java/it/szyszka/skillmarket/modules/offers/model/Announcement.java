package it.szyszka.skillmarket.modules.offers.model;

import it.szyszka.skillmarket.modules.user.model.User;

/**
 * Created by rafal on 08.12.17.
 */

public class Announcement {

    private Long id;
    private User advertiser;
    private Advertisement advertisement;
    private Advertisement.Character character;

    private Announcement(Long id, User advertiser, Advertisement advertisement, Advertisement.Character character) {
        this.id = id;
        this.advertiser = advertiser;
        this.advertisement = advertisement;
        this.character = character;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(User advertiser) {
        this.advertiser = advertiser;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public Advertisement.Character getCharacter() {
        return character;
    }

    public void setCharacter(Advertisement.Character character) {
        this.character = character;
    }

    public class Builder {
        private Long id;
        private User advertiser;
        private Advertisement advertisement;
        private Advertisement.Character character;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setAdvertiser(User advertiser) {
            this.advertiser = advertiser;
            return this;
        }

        public Builder setAdvertisement(Advertisement advertisement) {
            this.advertisement = advertisement;
            return this;
        }

        public Builder setCharacter(Advertisement.Character character) {
            this.character = character;
            return this;
        }

        public Announcement createAnnouncement() {
            return new Announcement(id, advertiser, advertisement, character);
        }
    }
}
