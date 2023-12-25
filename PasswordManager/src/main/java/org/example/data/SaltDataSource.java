package org.example.data;

import java.io.*;
import java.util.ArrayList;

public class SaltDataSource {

    public void storeSalt(byte [] salt) {

        try {
            OutputStream outputStream = new FileOutputStream("salt.txt");
            outputStream.write(salt);
            outputStream.close();
        } catch (Exception e) {
            System.out.println("couldn't write the salt");
        }
    }

    public byte[] getSalt() {
        byte[] chunk = new byte[1024];
        ArrayList<Byte> saltLinkedList = new ArrayList<>();
        try {
            InputStream inputStream = new FileInputStream("salt.txt");
            while (inputStream.read(chunk) != -1)
                for (byte b : chunk)
                    saltLinkedList.add(b);

            byte[] salt = new byte[saltLinkedList.size()];
            for (int i = 0; i < saltLinkedList.size(); i++) {
                salt[i] = saltLinkedList.get(i);
            }

            return salt;
        } catch (Exception e) {
            System.out.println("couldn't read the salt");
            return null;
        }
    }
}
