package com.example.andrearodriguez.androidchat.addcontact.ui;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public interface AddContactView {

    void showInput();
    void hideInput();
    void showProgress();
    void hidePRogress();

    void contactAdded();
    void contactNotAdded();
}
