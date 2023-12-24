package org.example.data;

import java.util.ArrayList;
import java.util.List;

public class PDataSourceIMP  implements PDataSource {

    // todo use the PasswordPakcageIOManager instead of this array list
    private ArrayList<EncryptedPPWithID> packagesWithID = new ArrayList<>();



    private long getAvailableId() {
        return packagesWithID.size();
    }


    @Override
    public long addNewPassword(EncryptedPasswordPackage password) {
        long id = getAvailableId();
        //boolean result = packagesWithID.add();
        return  0;
    }

    @Override
    public boolean updatePassword(long id, EncryptedPasswordPackage newPassword) {
        return false;
    }

    @Override
    public boolean deletePassword(long id) {
        return false;
    }

    @Override
    public EncryptedPasswordPackage getPasswordById(long id) {
        return null;
    }

    @Override
    public List<EncryptedPPWithID> getAllPPSortedById() {
        //  todo update array

        return packagesWithID;
    }
}
