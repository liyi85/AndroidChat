package com.example.andrearodriguez.androidchat.addcontact.ui;

import com.example.andrearodriguez.androidchat.addcontact.AddContactInteractor;
import com.example.andrearodriguez.androidchat.addcontact.AddContactlistPresenter;
import com.example.andrearodriguez.androidchat.addcontact.events.AddContactEvent;
import com.example.andrearodriguez.androidchat.lib.EvenBus;
import com.example.andrearodriguez.androidchat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public class AddContactlistPresenterImp implements AddContactlistPresenter {

    private EvenBus eventBus;
    private AddContactView view;
    private AddContactInteractor interactor;

    public AddContactlistPresenterImp(AddContactView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new AddContactInteractorImpl();
    }

    @Override
    public void onShow() {
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if(view!=null){
            view.hideInput();
            view.showProgress();
        }
        interactor.excute(email);
    }

    @Override
    @Subscribe
    public void onEventMaininTheread(AddContactEvent event) {
        if(view!=null){
            view.hidePRogress();
            view.showInput();

            if(event.isError()){
                view.contactNotAdded();
            }else{
                view.contactAdded();
            }
        }
    }
}
