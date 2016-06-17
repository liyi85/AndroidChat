package com.example.andrearodriguez.androidchat.contactslist;

/**
 * Created by andrearodriguez on 6/16/16.
 */
public interface ContectListInteractor {
    void suscribe();
    void unsuscribe();
    void destroyListener();
    void removeContact(String email);
}
