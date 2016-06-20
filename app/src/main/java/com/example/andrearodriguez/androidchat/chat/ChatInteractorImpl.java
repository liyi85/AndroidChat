package com.example.andrearodriguez.androidchat.chat;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public class ChatInteractorImpl implements ChatInteractor {

    ChatRepository repository;

    public ChatInteractorImpl() {
        this.repository = new ChatRepositoryImpl();
    }

    @Override
    public void sendMessage(String msg) {
        repository.sendMessage(msg);
    }

    @Override
    public void setRecipies(String recipies) {
        repository.setRecipies(recipies);
    }

    @Override
    public void suscribe() {
        repository.suscribe();
    }

    @Override
    public void unsuscribe() {
        repository.unsuscribe();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }
}
