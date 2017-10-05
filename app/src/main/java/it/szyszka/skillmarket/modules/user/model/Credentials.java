package it.szyszka.skillmarket.modules.user.model;

import android.util.Base64;

/**
 * Created by rafal on 05.10.17.
 */

public class Credentials {

    private String email;
    private String hashedPass;

    private static Credentials userCredentials;

    private Credentials(String email, String hashedPass) {
        this.email = email;
        this.hashedPass = hashedPass;
    }

    public static Credentials instantiate(String email, String hashedPass) {
        userCredentials = new Credentials(email, hashedPass);
        return userCredentials;
    }

    public static Credentials getInstance() {
        return userCredentials;
    }

    public String getBasicAuth() {
        StringBuilder builder = new StringBuilder(email);
        String authKey = Base64.encodeToString(
                builder.append(":").append(hashedPass).toString().getBytes(),
                Base64.NO_WRAP
        );
        return "Basic " + authKey.trim();
    }
}
