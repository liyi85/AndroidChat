package com.example.andrearodriguez.androidchat.chat;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public class ChatSessionInteractorImpl implements ChatSessionInteractor {

    ChatRepository repository;

    public ChatSessionInteractorImpl() {
        this.repository = new ChatRepositoryImpl();
    }

    @Override
    public void changeConetionstatus(boolean online) {
    repository.changeConetionstatus(online);
    }
}
