package com.example.andrearodriguez.androidchat.chat;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public interface ChatRepository {
    void sendMessage(String msg);
    void setRecipies(String recipies);

    void suscribe();
    void unsuscribe();
    void destroyListener();
    void changeConetionstatus(boolean online);

}
