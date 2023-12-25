package org.example.security;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class SecretKeyGenerator {

    public static byte[] generateSalt(int length) {
        byte[] salt = new byte[length];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        return salt;
    }
    public static Key getPasswordBasedKey(int keySize, char[] password, byte[] salt) {

        String algorithm = "PBKDF2WithHmacSHA256";

        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, 1000, keySize);
            SecretKey pbeKey = SecretKeyFactory.getInstance(algorithm).generateSecret(pbeKeySpec);
            return new SecretKeySpec(pbeKey.getEncoded(), algorithm);
        } catch (Exception e) {
            System.out.println("Couldn't generate a key from password");
            return null;
        }
    }

}
