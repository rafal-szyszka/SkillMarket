package it.szyszka.skillmarket.modules.user.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by rafal on 30.09.17.
 */

public class User {

    @Getter @Setter private Long id;
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

    public static String toJSON(User user) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
