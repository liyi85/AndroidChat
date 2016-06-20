package com.example.andrearodriguez.androidchat.chat;

import com.example.andrearodriguez.androidchat.entities.ChatMessagge;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public interface ChatView {
    void onMessaggeReceived(ChatMessagge msg);
}
