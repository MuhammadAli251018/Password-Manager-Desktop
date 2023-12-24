package org.example.data;

import java.util.List;

public class PDataSourceIMP  implements PDataSource {

    public PDataSourceIMP(PasswordPackagesIOManager ioManager) {
        this.ioManager = ioManager;
    }

    PasswordPackagesIOManager ioManager;

    @Override
    public long addNewPassword(EncryptedPasswordPackage password) {
        return ioManager.addNewPassword(password);
    }

    @Override
    public boolean updatePassword(long id, EncryptedPasswordPackage newPassword) {
        return ioManager.updatePassword(id, newPassword);
    }

    @Override
    public boolean deletePassword(long id) {
        return ioManager.deletePassword(id);
    }

    @Override
    public EncryptedPasswordPackage getPasswordById(long id) {
        return ioManager.getPasswordById(id);
    }

    @Override
    public List<EncryptedPPWithID> getAllPPSortedById() {
        return ioManager.getAllPPSortedById();
    }
}
