package com.example.andrearodriguez.androidchat.login;

/**
 * Created by andrearodriguez on 6/9/16.
 */
public interface LogingPresenter {
    void onDestroy();
    void checkForAuthenticationUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
}
