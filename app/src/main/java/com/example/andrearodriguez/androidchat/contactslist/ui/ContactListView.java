package com.example.andrearodriguez.androidchat.contactslist.ui;

import com.example.andrearodriguez.androidchat.entities.User;

/**
 * Created by andrearodriguez on 6/16/16.
 */
public interface ContactListView {
    void onContactAdd(User user);
    void onContactChange(User user);
    void onContactRemoved(User user);

}
