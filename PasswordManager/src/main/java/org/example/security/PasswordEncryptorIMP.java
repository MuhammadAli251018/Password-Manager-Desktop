package org.example.security;

import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncryptorIMP implements PasswordEncryptor{
    private Cipher cipher;
    {
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        }
        catch (Exception e) {
            System.out.println("couldn't initialize cipher");
        }
    }

    @Override
    public String encryptPassword(String key, String password) {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(),"AES");
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherText = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(cipherText);

        } catch (Exception e) {
            System.out.println("couldn't encrypt");
            return null;
        }
    }

    @Override
    public String decryptPassword(String key, String encryptedPassword) {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(),"AES");
        try{
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(plainText);
        } catch (Exception e) {
            System.out.println("couldn't decrypt");
            return null;
        }
    }
}
