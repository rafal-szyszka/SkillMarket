package it.szyszka.skillmarket.modules.user.model;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by rafal on 30.09.17.
 */

public class User {

    @Getter @Setter private String nickname;
    @Getter @Setter private String email;

    @Getter @Setter private String fullName;
    @Getter @Setter private String password;

    @Getter @Setter private String city;
    @Getter @Setter private String mailingAddress;
    @Getter @Setter private String phoneNumber;

    @Getter @Setter private String about;
    @Getter @Setter private String preferredContact;

    @Getter @Setter private Set<User> friends;
    @Getter @Setter private Set<User> trusted;

}
