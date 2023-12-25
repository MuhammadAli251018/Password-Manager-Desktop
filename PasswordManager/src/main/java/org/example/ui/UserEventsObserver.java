package org.example.ui;

public interface UserEventsObserver {

    void addNewPasswordEvent(String account, String password, String website);

    void updatePasswordEvent(int id, String account, String password, String website);

    void deletePasswordEvent(int id);

    void getPasswordEvent(int id);

    void getAllPasswordsEvent();

    void deleteAllPasswords();
}
