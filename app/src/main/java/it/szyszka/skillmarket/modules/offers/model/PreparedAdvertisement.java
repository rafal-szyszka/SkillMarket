package it.szyszka.skillmarket.modules.offers.model;

/**
 * Created by rafal on 08.12.17.
 */

public class PreparedAdvertisement {

    private Long advertiserId;
    private Advertisement advertisement;
    private Advertisement.Character character;

    public PreparedAdvertisement(Long advertiserId, Advertisement advertisement, Advertisement.Character character) {
        this.advertiserId = advertiserId;
        this.advertisement = advertisement;
        this.character = character;
    }

    public PreparedAdvertisement() {

    }

    public Long getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Long advertiserId) {
        this.advertiserId = advertiserId;
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
}
