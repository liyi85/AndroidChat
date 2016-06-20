package com.example.andrearodriguez.androidchat.addcontact;

import com.example.andrearodriguez.androidchat.addcontact.events.AddContactEvent;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public interface AddContactlistPresenter {

    void onShow();
    void onDestroy();

    void addContact (String email);
    void onEventMaininTheread(AddContactEvent event);
}
