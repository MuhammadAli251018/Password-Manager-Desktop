package org.example.data;

import java.util.List;

public interface PDataSource {

    /** returns the ID of the package*/
    long addNewPassword(EncryptedPasswordPackage password);

    boolean updatePassword(long id, EncryptedPasswordPackage newPassword);

    boolean deletePassword(long id);

    EncryptedPasswordPackage getPasswordById(long id);

    List<EncryptedPPWithID> getAllPPSortedById();
}
