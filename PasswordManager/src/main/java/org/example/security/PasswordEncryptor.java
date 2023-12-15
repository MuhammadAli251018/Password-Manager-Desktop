package org.example.security;

public interface PasswordEncryptor {
    String encryptPassword(String key, String password);

    String decryptPassword(String key, String encryptedPassword);
}
