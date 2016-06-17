package com.example.andrearodriguez.androidchat.contactslist;

import com.example.andrearodriguez.androidchat.contactslist.events.ContactListEvent;

/**
 * Created by andrearodriguez on 6/16/16.
 */
public interface ContactListPresenter {
    void onPause();
    void onResume();
    void onCreated();
    void onDestroy();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);
}
