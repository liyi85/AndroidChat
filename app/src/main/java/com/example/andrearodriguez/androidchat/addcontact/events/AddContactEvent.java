package com.example.andrearodriguez.androidchat.addcontact.events;

/**
 * Created by andrearodriguez on 6/19/16.
 */
public class AddContactEvent {

    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
