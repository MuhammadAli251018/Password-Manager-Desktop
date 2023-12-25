package org.example.ui;

import org.example.data.EncryptedPPWithID;
import org.example.data.EncryptedPasswordPackage;
import org.example.data.PDataSource;
import org.example.security.PasswordEncryptor;

import java.util.List;

public class UEObserverIMP implements UserEventsObserver {

    public  UEObserverIMP(PDataSource dataSource, PasswordEncryptor encryptor, String userKey) {
        this.dataSource = dataSource;
        this.encryptor = encryptor;
        this.userKey = userKey;
    }

    PDataSource dataSource;
    PasswordEncryptor encryptor;
    String userKey;

    @Override
    public void addNewPasswordEvent(String account, String password, String website) {
        String encryptedPassword = encryptor.encryptPassword(userKey, password);
        long id = dataSource.addNewPassword(
                new EncryptedPasswordPackage(
                        encryptedPassword,
                        account,
                        website
                )
        );

        System.out.println("added password with id: " + id);
    }

    @Override
    public void updatePasswordEvent(int id, String account, String password, String website) {
        String encryptedPassword = encryptor.encryptPassword(userKey, password);
        boolean result = dataSource.updatePassword(
                id,
                new EncryptedPasswordPackage(
                        encryptedPassword,
                        account,
                        website
                )
        );

        if (result)
            System.out.println("updated password with id: " + id);
        else
            System.out.println("couldn't update");
    }

    @Override
    public void deletePasswordEvent(int id) {
        boolean result = dataSource.deletePassword(
                id
        );

        if (result)
            System.out.println("deleted password with id: " + id);
        else
            System.out.println("couldn't delete");
    }

    @Override
    public void getPasswordEvent(int id) {
        EncryptedPasswordPackage passwordPackage = dataSource.getPasswordById(id);
        if (passwordPackage != null)
            System.out.println(
                    "Password: " + encryptor.decryptPassword(userKey, passwordPackage.getEncryptedPassword()) +
                            " Account: " + passwordPackage.getAccount() + " Website: " + passwordPackage.getWebsite()
            );
        else
            System.out.println("couldn't get the password");
    }

    @Override
    public void getAllPasswordsEvent() {
        List<EncryptedPPWithID> packages = dataSource.getAllPPSortedById();
        for (EncryptedPPWithID pPackage : packages) {
            EncryptedPasswordPackage passwordPackage = pPackage.getPasswordPackage();
            if (passwordPackage != null)
                System.out.println(
                        "ID: " + pPackage.getId() +
                                " Account: " + passwordPackage.getAccount() + " Website: " + passwordPackage.getWebsite()
                );
            else
                System.out.println("couldn't get passwords");
        }
    }

    @Override
    public void deleteAllPasswords() {
        List<EncryptedPPWithID> packages = dataSource.getAllPPSortedById();
        for (EncryptedPPWithID pPackage : packages) {
            boolean result = dataSource.deletePassword(pPackage.getId());
            if (result)
                System.out.println("Deleted all passwords");
            else
                System.out.println("couldn't get the delete");
        }
    }
}
