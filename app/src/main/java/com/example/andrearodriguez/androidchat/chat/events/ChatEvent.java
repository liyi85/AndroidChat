package com.example.andrearodriguez.androidchat.chat.events;

import com.example.andrearodriguez.androidchat.entities.ChatMessagge;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public class ChatEvent {
    ChatMessagge messagge;

    public ChatMessagge getMessagge() {
        return messagge;
    }

    public void setMessagge(ChatMessagge messagge) {
        this.messagge = messagge;
    }
}
