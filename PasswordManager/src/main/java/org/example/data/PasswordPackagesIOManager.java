package org.example.data;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;


//  todo implement the operation on the packages list
public class PasswordPackagesIOManager {
    private final static char outerPackageSplitter = '|';
    private final static char innerPackageSplitter = ',';
    private final static String dataFileLocation = "data.txt";
    private LinkedList<EncryptedPPWithID> packagesWithID = new LinkedList<>();
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

    private String readPackagesFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataFile));
            String data = "";
            String temp;

            while ((temp  = br.readLine()) != null) {
                data += temp;
            }

            return data;
        }catch (Exception e) {
            System.out.println("Error while reading data file: " + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    private LinkedList<String> splitData(String strData, char splitter) {
        String strPackage = "";
        LinkedList<String> strPackages = new LinkedList<>();


        for (int i =0; i < strData.length(); i ++) {
            if (strData.charAt(i) == splitter) {
                strPackages.add(strPackage);
                strPackage = "";
            }
            else {
                strPackage += strData.charAt(i);
            }
        }

        return strPackages;
    }

    private LinkedList<EncryptedPPWithID> getPackages(LinkedList<String> strPackages) {
        LinkedList<EncryptedPPWithID> packages = new LinkedList<>();

        for (int i = 0; i < strPackages.size(); i ++) {
            String strPackage = strPackages.get(i);
            LinkedList<String> attributes = splitData(strPackage, innerPackageSplitter);

            if (attributes.size() != 4) {
                System.out.println("couldn't resolve data");
            }

            EncryptedPasswordPackage pPackage = new EncryptedPasswordPackage(
                    attributes.get(3),
                    attributes.get(2),
                    attributes.get(1)
            );

            EncryptedPPWithID packageWithId = new EncryptedPPWithID(
                    pPackage,
                    Long.parseLong(attributes.get(0))

            );

            packages.add(packageWithId);
        }
        return packages;
    }

    private boolean updatePackagesWithId() {
        try {
            // read the string from the file
            String strData = readPackagesFile();

            // convert string to packages array
            LinkedList<String> strPackages = splitData(strData, outerPackageSplitter);

            packagesWithID = getPackages(strPackages);

            return true;

        } catch (Exception e) {
            System.out.println("couldn't read the data");
            return false;
        }
    }


}
