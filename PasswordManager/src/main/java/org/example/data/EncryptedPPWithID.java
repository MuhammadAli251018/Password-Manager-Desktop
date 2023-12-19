package org.example.data;

public class EncryptedPPWithID {
    private EncryptedPasswordPackage passwordPackage;
    private long Id;

    public EncryptedPPWithID(EncryptedPasswordPackage passwordPackage, long id) {
        this.passwordPackage = passwordPackage;
        Id = id;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public EncryptedPasswordPackage getPasswordPackage() {
        return passwordPackage;
    }

    public void setPasswordPackage(EncryptedPasswordPackage passwordPackage) {
        this.passwordPackage = passwordPackage;
    }
}
