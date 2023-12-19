package org.example.data;

import java.io.*;
import java.util.ArrayList;


public class PasswordPackagesIOManager {
    private final static String DATA_LOCATION = "data.txt";
    private final static char outerPackageSplitter = '|';
    private final static char innerPackageSplitter = ',';
    private final static String dataFileLocation = "data.txt";
    private ArrayList<EncryptedPPWithID> packagesWithID = new ArrayList<>();
    private File dataFile;

    {
        try {
            dataFile = new File(dataFileLocation);
        } catch (Exception e) {
            System.out.println("Error while creating file object");
        }
    }

    private String encodeDataToString() {
        String data = "";

        for (int i = 0; i < packagesWithID.size(); i ++) {
            data += packagesWithID.get(i).getId() + innerPackageSplitter;
            data += packagesWithID.get(i).getPasswordPackage().getWebsite() + innerPackageSplitter;
            data += packagesWithID.get(i).getPasswordPackage().getAccount() + innerPackageSplitter;
            data += packagesWithID.get(i).getPasswordPackage().getEncryptedPassword() + outerPackageSplitter;

        }

        return data;
    }
    private boolean updateTheFile() {
        try {
            FileWriter writer = new FileWriter(dataFile);
            writer.write(encodeDataToString());
            return true;
        } catch (IOException e) {
            System.out.println("Error while updating file");
            return false;
        }
    }


}
