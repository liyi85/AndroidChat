package com.example.andrearodriguez.androidchat.login;

import com.example.andrearodriguez.androidchat.domain.FirebaseHelper;
import com.example.andrearodriguez.androidchat.lib.EvenBus;
import com.example.andrearodriguez.androidchat.lib.GreenRobotEventBus;
import com.example.andrearodriguez.androidchat.login.events.LoginEvent;

/**
 * Created by andrearodriguez on 6/11/16.
 */
public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper helper;

    public LoginRepositoryImpl(){
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signUp(String email, String password) {
        postEvent(LoginEvent.onSignUpSuccess);

    }

    @Override
    public void SignIn(String email, String password) {

        postEvent(LoginEvent.onSignInSuccess);    }

    @Override
    public void checkSession() {
        postEvent(LoginEvent.onFailedRecoverSession);

    }
    private void postEvent(int type, String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(errorMessage !=null){
            loginEvent.setErrorMessage(errorMessage);
        }

        EvenBus evenBus = GreenRobotEventBus.getInstance();
        evenBus.post(loginEvent);

    }
    private void postEvent(int type){
        postEvent(type, null);

    }
}
