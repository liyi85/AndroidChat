package com.example.andrearodriguez.androidchat.contactslist;

/**
 * Created by andrearodriguez on 6/17/16.
 */
public class ContactListSessionInteractorImp implements ContactListSessionInteractor {

    ContactListRepository repository;

    public ContactListSessionInteractorImp() {
        repository = new ContactListRepositoryImpl();
    }

    @Override
    public void signOff() {
        repository.signOff();

    }

    @Override
    public String getCurrentUserEmail() {
        return repository.getCurrentUserEmail();
    }

    @Override
    public void changeConectionStatus(boolean online) {
        repository.changeConectionStatus(online);

    }
}
