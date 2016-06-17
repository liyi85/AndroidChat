package com.example.andrearodriguez.androidchat.contactslist;

/**
 * Created by andrearodriguez on 6/16/16.
 */
public interface ContactListSessionInteractor {
    void signOff();
    String getCurrentUserEmail();
    void changeConectionStatus(boolean online);
}
