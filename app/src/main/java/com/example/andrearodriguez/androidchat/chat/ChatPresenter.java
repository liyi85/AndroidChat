package com.example.andrearodriguez.androidchat.chat;

import com.example.andrearodriguez.androidchat.chat.events.ChatEvent;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipies(String recipies);
    void sendMessagge(String msg);
    void onEventMaintTnread(ChatEvent event);
}
