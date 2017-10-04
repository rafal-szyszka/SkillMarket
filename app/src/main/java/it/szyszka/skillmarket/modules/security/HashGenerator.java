package it.szyszka.skillmarket.modules.security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by rafal on 04.10.17.
 */

public class HashGenerator {

    public static String generateSHA256Key(String input) {
        return new String(Hex.encodeHex(DigestUtils.sha256(input)));
    }

}
