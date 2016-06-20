package com.example.andrearodriguez.androidchat.chat;

import com.example.andrearodriguez.androidchat.chat.events.ChatEvent;
import com.example.andrearodriguez.androidchat.domain.FirebaseHelper;
import com.example.andrearodriguez.androidchat.entities.ChatMessagge;
import com.example.andrearodriguez.androidchat.lib.EvenBus;
import com.example.andrearodriguez.androidchat.lib.GreenRobotEventBus;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public class ChatRepositoryImpl implements ChatRepository {

    private String recipients;
    private EvenBus evenBus;
    private FirebaseHelper helper;
    private ChildEventListener chatEventListener;

    public ChatRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.evenBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void sendMessage(String msg) {
        ChatMessagge chatMessagge = new ChatMessagge();
        chatMessagge.setSender(helper.getAuthUserEmail());
        chatMessagge.setMsg(msg);

        Firebase chatsReferences = helper.getChatsReferrence(recipients);
        chatsReferences.push().setValue(chatMessagge);
    }

    @Override
    public void setRecipies(String recipies) {
        this.recipients = recipies;
    }

    @Override
    public void suscribe() {
        if(chatEventListener == null){
            chatEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    ChatMessagge chatMessagge = dataSnapshot.getValue(ChatMessagge.class);
                    String msmSender = chatMessagge.getSender();

                    chatMessagge.setSentByMe(msmSender.equals(helper.getAuthUserEmail()));

                    ChatEvent chatEvent = new ChatEvent();
                    chatEvent.setMessagge(chatMessagge);
                    evenBus.post(chatEvent);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            };
        }
        helper.getChatsReferrence(recipients).addChildEventListener(chatEventListener);
    }

    @Override
    public void unsuscribe() {
        if(chatEventListener!=null){
            helper.getChatsReferrence(recipients).removeEventListener(chatEventListener);
        }
    }

    @Override
    public void destroyListener() {
        chatEventListener = null;
    }

    @Override
    public void changeConetionstatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }
}
