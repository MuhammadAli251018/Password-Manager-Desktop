package org.example.data;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


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
            if (!dataFile.exists())
                dataFile.createNewFile();

            if(!updatePackagesWithId())
                System.out.println("couldn't read stored data");
        } catch (Exception e) {
            System.out.println("Error while creating file object");
        }
    }

    private String encodeDataToString() {
        String data = "";

        for (EncryptedPPWithID encryptedPPWithID : packagesWithID) {
            data += encryptedPPWithID.getId() + "" + innerPackageSplitter;
            data += encryptedPPWithID.getPasswordPackage().getWebsite() + innerPackageSplitter;
            data += encryptedPPWithID.getPasswordPackage().getAccount() + innerPackageSplitter;
            data += encryptedPPWithID.getPasswordPackage().getEncryptedPassword() + outerPackageSplitter;
        }

        return data;
    }
    private boolean updateTheFile() {
        try {
            FileWriter writer = new FileWriter(dataFile);
            writer.write(encodeDataToString());
            writer.close();
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

            br.close();
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
            if (strData.charAt(i) == splitter || i == strData.length() - 1) {
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

        for (String strPackage : strPackages) {
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

    private long getAvailableID() {
        return packagesWithID.size();
    }

    public long addNewPassword(EncryptedPasswordPackage password) {
        long id = getAvailableID();
        packagesWithID.add(
                new EncryptedPPWithID(
                        password,
                        id

                )
        );

        if (updateTheFile())
            return id;
        else
            return -1;
    }

    public boolean updatePassword(long id, EncryptedPasswordPackage newPassword) {

        packagesWithID.add(
                new EncryptedPPWithID(
                        newPassword,
                        id
                )
        );

        return updateTheFile();
    }

    public boolean deletePassword(long id) {
        if (id < getAvailableID()) {
            packagesWithID.remove(id);
            return updateTheFile();
        }
        else
            return false;
    }

    public  EncryptedPasswordPackage getPasswordById(long id){
        return packagesWithID.get((int) id).getPasswordPackage();
    }

    public List<EncryptedPPWithID> getAllPPSortedById() {
        return packagesWithID;
    }
}
