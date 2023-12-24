package org.example.security;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Random;

public class SecretKeyGenerator {
    public String generateSecretKey(String passowrd){

        byte[] array = new byte[16-passowrd.length()];
        new Random().nextBytes(array);
        String generatedSalt = new String(array, Charset.forName("UTF-8"));
        String generatedKey = passowrd + generatedSalt;

        return generatedKey;
    }

}
