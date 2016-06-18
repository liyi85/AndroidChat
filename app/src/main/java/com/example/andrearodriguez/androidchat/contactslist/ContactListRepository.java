package com.example.andrearodriguez.androidchat.contactslist;

/**
 * Created by andrearodriguez on 6/16/16.
 */
public interface ContactListRepository {
    void signOff();
    String getCurrentUserEmail();
    void changeConectionStatus(boolean online);
    void removeContact(String email);
    void destroyListener();
    void suscribeToContactListEvent();
    void unsuscribeToContactListEvent();

}
