package org.example.security;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncryptorIMP implements PasswordEncryptor{

    /*  KeySecret is an object you should use to encrypt and decrypt the data
        here the class methods accept the key in a string format, so we need to
        get a secret object from the string, and this is the purpose of the encode
        and decode functions

        use this which explains how to decode and encode the key:
        https://stackoverflow.com/questions/5355466/converting-secret-key-into-a-string-and-vice-versa
        
    */

    // private static final String AES = "AES";
    // private final Key key;

    // testing purpose
    public static void main(String[] args) {
        PasswordEncryptorIMP imp = new PasswordEncryptorIMP();
        String encoded = imp.encodeSecretKey("testback");
        String decoded = imp.decodeSecretKey(encoded);
        System.out.println(encoded);
        System.out.println(decoded);
    }

   
    // public SecretKeySpec encodeSecretKey(String key) {
    //     // Todo: create a secret key object and return it
    //     byte[] decodedKey = Base64.getDecoder().decode(key);
    //     return new SecretKeySpec(decodedKey,0,decodedKey.length,"AES");
    // }
    private String encodeSecretKey(String key) {
        // Todo: create a secret key object and return it
        return Base64.getEncoder().encodeToString(key.getBytes());
    }
    
    private String decodeSecretKey(String key) {
        // Todo: get the string out of the key secret
        byte[] decodedKey = Base64.getDecoder().decode(key);
        return new String(decodedKey);
    }
    @Override
    public String encryptPassword(String key, String password) {
        // TODO: Encrypt the password and return the encrypted pass
        return null;
    }

    @Override
    public String decryptPassword(String key, String encryptedPassword) {
        // TODO: Decrypt the encrypt password and return the pass
        return null;
    } 


}
