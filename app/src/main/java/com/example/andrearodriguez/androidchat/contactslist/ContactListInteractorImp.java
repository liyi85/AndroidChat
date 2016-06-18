package com.example.andrearodriguez.androidchat.contactslist;

/**
 * Created by andrearodriguez on 6/17/16.
 */
public class ContactListInteractorImp implements ContectListInteractor {

    ContactListRepository repository;

    public ContactListInteractorImp() {
        repository = new ContactListRepositoryImpl();
    }

    @Override
    public void suscribe() {
        repository.suscribeToContactListEvent();

    }

    @Override
    public void unsuscribe() {
        repository.unsuscribeToContactListEvent();

    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }

    @Override
    public void removeContact(String email) {
        repository.removeContact(email);
    }
}
