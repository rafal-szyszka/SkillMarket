package it.szyszka.skillmarket.modules.user.model;

import android.support.annotation.NonNull;
import android.util.Base64;

/**
 * Created by rafal on 05.10.17.
 */

public class Credentials {

    private String email;
    private String hashedPass;
    private String BASE_64;

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
        if(BASE_64 == null) {
            BASE_64 = countBase64();
        }
        return BASE_64;
    }

    @NonNull
    private String countBase64() {
        StringBuilder builder = new StringBuilder(email);
        String authKey = Base64.encodeToString(
                builder.append(":").append(hashedPass).toString().getBytes(),
                Base64.NO_WRAP
        );
        return "Basic " + authKey.trim();
    }

    public static void clear() {
        userCredentials.BASE_64 = "";
    }
}
