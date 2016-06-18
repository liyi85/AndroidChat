package com.example.andrearodriguez.androidchat.contactslist;

import com.example.andrearodriguez.androidchat.contactslist.events.ContactListEvent;
import com.example.andrearodriguez.androidchat.contactslist.ui.ContactListView;
import com.example.andrearodriguez.androidchat.entities.User;
import com.example.andrearodriguez.androidchat.lib.EvenBus;
import com.example.andrearodriguez.androidchat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 6/17/16.
 */
public class ContactListPresenterImp implements ContactListPresenter {

    private EvenBus evenBus;
    private ContactListView view;
    private ContectListInteractor listInteractor;
    private ContactListSessionInteractor sessionInteractor;

    public ContactListPresenterImp(ContactListView view) {
        this.view = view;
        evenBus = GreenRobotEventBus.getInstance();
        this.listInteractor = new ContactListInteractorImp();
        this.sessionInteractor = new ContactListSessionInteractorImp();
    }

    @Override
    public void onPause() {
        sessionInteractor.changeConectionStatus(User.OFFLINE);
        listInteractor.unsuscribe();
    }

    @Override
    public void onResume() {
        sessionInteractor.changeConectionStatus(User.ONLINE);
        listInteractor.suscribe();
    }

    @Override
    public void onCreate() {
        evenBus.register(this);
    }

    @Override
    public void onDestroy() {
        listInteractor.destroyListener();
        evenBus.unregister(this);
        view = null;
    }

    @Override
    public void signOff() {
        sessionInteractor.changeConectionStatus(User.OFFLINE);
        listInteractor.unsuscribe();
        listInteractor.destroyListener();
        sessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return sessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        listInteractor.removeContact(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch (event.getEventType()){
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;

            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;

            case ContactListEvent.onContactRemoved:
                onContactRemoved(user);
                break;
        }

    }

    private void onContactAdded(User user){
        if(view!=null){
            view.onContactAdd(user);
        }
    }

    private void onContactChanged(User user){
        if(view!=null){
            view.onContactChange(user);
        }
    }

    private void onContactRemoved(User user){
        if(view!=null){
            view.onContactRemoved(user);
        }
    }
}
