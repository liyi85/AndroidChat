package com.example.andrearodriguez.androidchat.login.ui;

/**
 * Created by andrearodriguez on 6/9/16.
 */
public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void navigationToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);

}
