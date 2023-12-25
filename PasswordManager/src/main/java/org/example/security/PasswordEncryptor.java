package org.example.security;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface PasswordEncryptor {
    String encryptPassword(String key, String password);

    String decryptPassword(String key, String encryptedPassword);
}
