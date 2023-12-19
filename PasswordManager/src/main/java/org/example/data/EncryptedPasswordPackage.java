package org.example.data;


public class EncryptedPasswordPackage {
    private final String encryptedPassword;
    private final String account;
    private final String website;

    public EncryptedPasswordPackage(String encryptedPassword, String account, String website) {
        this.encryptedPassword = encryptedPassword;
        this.account = account;
        this.website = website;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getAccount() {
        return account;
    }

    public String getWebsite() {
        return website;
    }
}
