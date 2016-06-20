package com.example.andrearodriguez.androidchat.addcontact.ui;

import com.example.andrearodriguez.androidchat.addcontact.AddContactInteractor;
import com.example.andrearodriguez.androidchat.addcontact.AddContactlistRepository;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public class AddContactInteractorImpl implements AddContactInteractor {

    AddContactlistRepository repository;

    public AddContactInteractorImpl() {
        repository = new AddContactRepositoryImpl();
    }

    @Override
    public void excute(String email) {
        repository.addContact(email);

    }
}
