package org.example;

import org.example.data.*;
import org.example.security.PasswordEncryptorIMP;
import org.example.security.SecretKeyGenerator;
import org.example.ui.AppUI;
import org.example.ui.UEObserverIMP;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File salt = new File("salt.txt");
        if (salt.exists()) {
            startApp();
        } else {
            createEncryptionKey();
            startApp();
        }

       // new PasswordPackagesIOManager();

    }

    private static void createEncryptionKey() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your new password: ");
        String password = scanner.next();

        byte [] salt = SecretKeyGenerator.generateSalt(256 - password.length());
        SaltDataSource saltDataSource = new SaltDataSource();
        saltDataSource.storeSalt(salt);

    }

    private static void startApp() {
        SaltDataSource saltDataSource = new SaltDataSource();
        byte[] salt = saltDataSource.getSalt();
       // System.out.println(Arrays.toString(salt));
        Scanner scanner = new Scanner(System.in);
       System.out.print("Enter your password to pass: ");
        String password = scanner.next();
        Key keyBytes = SecretKeyGenerator.getPasswordBasedKey(256, password.toCharArray(), salt);
        //System.out.println(Arrays.toString(keyBytes.getEncoded()));
        String key = Base64.getEncoder().encodeToString(keyBytes.getEncoded());


        AppUI ui = new AppUI(
                new UEObserverIMP(
                        new PDataSourceIMP(
                                new PasswordPackagesIOManager()
                        ),
                        new PasswordEncryptorIMP(),
                        key
                )
        );

        ui.startApplication();
    }

}