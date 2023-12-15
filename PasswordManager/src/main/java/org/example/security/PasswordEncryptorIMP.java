package org.example.security;

import javax.crypto.SecretKey;

public class PasswordEncryptorIMP implements PasswordEncryptor{

    /*  KeySecret is an object you should use to encrypt and decrypt the data
        here the class methods accept the key in a string format, so we need to
        get a secret object from the string, and this is the purpose of the encode
        and decode functions

        use this which explains how to decode and encode the key:
        https://stackoverflow.com/questions/5355466/converting-secret-key-into-a-string-and-vice-versa
        دونت ورري
    */



    private SecretKey encodeSecretKey(String key) {
        // Todo: create a secret key object and return it
        return null;
    }

    private String decodeSecretKey(SecretKey key) {
        // Todo: get the string out of the key secret
        return null;
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
