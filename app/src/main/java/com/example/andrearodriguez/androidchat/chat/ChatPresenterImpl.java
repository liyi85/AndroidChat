package com.example.andrearodriguez.androidchat.chat;

import com.example.andrearodriguez.androidchat.chat.events.ChatEvent;
import com.example.andrearodriguez.androidchat.chat.ui.ChatView;
import com.example.andrearodriguez.androidchat.entities.User;
import com.example.andrearodriguez.androidchat.lib.EvenBus;
import com.example.andrearodriguez.androidchat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public class ChatPresenterImpl implements ChatPresenter {
    private EvenBus evenBus;
    private ChatView view;
    private ChatInteractor chatInteractor;
    private ChatSessionInteractor chatSessionInteractor;

    public ChatPresenterImpl(ChatView view) {
        this.view= view;
        this.evenBus = GreenRobotEventBus.getInstance();
        this.chatInteractor = new ChatInteractorImpl();
        this.chatSessionInteractor = new ChatSessionInteractorImpl();
    }

    @Override
    public void onPause() {
        chatInteractor.unsuscribe();
        chatSessionInteractor.changeConetionstatus(User.OFFLINE);
    }

    @Override
    public void onResume() {
        chatInteractor.suscribe();
        chatSessionInteractor.changeConetionstatus(User.ONLINE);
    }

    @Override
    public void onCreate() {
        evenBus.register(this);
    }

    @Override
    public void onDestroy() {
        evenBus.unregister(this);
        chatInteractor.destroyListener();
        view = null;
    }

    @Override
    public void setChatRecipies(String recipies) {
        chatInteractor.setRecipies(recipies);
    }

    @Override
    public void sendMessagge(String msg) {
        chatInteractor.sendMessage(msg);
    }

    @Override
    @Subscribe
    public void onEventMaintTnread(ChatEvent event) {
        if(view != null){
            view.onMessaggeReceived(event.getMessagge());
        }
    }
}
