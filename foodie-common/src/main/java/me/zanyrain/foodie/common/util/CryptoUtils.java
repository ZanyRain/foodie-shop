package me.zanyrain.foodie.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CryptoUtils {
    public static String encodeUserPassword(String password) {
        try {
            return Base64.getEncoder()
                    .encodeToString(MessageDigest.getInstance("md5")
                            .digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
