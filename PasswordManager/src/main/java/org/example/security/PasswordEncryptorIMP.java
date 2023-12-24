package org.example.security;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Random;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncryptorIMP implements PasswordEncryptor{
    public PasswordEncryptorIMP() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    /*  KeySecret is an object you should use to encrypt and decrypt the data
        here the class methods accept the key in a string format, so we need to
        get a secret object from the string, and this is the purpose of the encode
        and decode functions

        use this which explains how to decode and encode the key:
        https://stackoverflow.com/questions/5355466/converting-secret-key-into-a-string-and-vice-versa
        
    */


    private Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

    @Override
    public String encryptPassword(String key, String password) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // TODO: Encrypt the password and return the encrypted pass
        SecretKey secretKey = new SecretKeySpec(key.getBytes(),"AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherText = cipher.doFinal(password.getBytes());

        return Base64.getEncoder().encodeToString(cipherText);

    }

    @Override
    public String decryptPassword(String key, String encryptedPassword) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // TODO: Decrypt the encrypt password and return the pass
        SecretKey secretKey = new SecretKeySpec(key.getBytes(),"AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(plainText);
    }


}
