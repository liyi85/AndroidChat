package com.example.andrearodriguez.androidchat.login;

import com.example.andrearodriguez.androidchat.domain.FirebaseHelper;
import com.example.andrearodriguez.androidchat.entities.User;
import com.example.andrearodriguez.androidchat.lib.EvenBus;
import com.example.andrearodriguez.androidchat.lib.GreenRobotEventBus;
import com.example.andrearodriguez.androidchat.login.events.LoginEvent;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by andrearodriguez on 6/11/16.
 */
public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper helper;
    private Firebase dataReference;
    private Firebase myUserReference;

    public LoginRepositoryImpl(){

        this.helper = FirebaseHelper.getInstance();
        this.dataReference = helper.getDataReference();
        this.myUserReference = helper.getMyUserReference();
    }


    @Override
    public void signUp(final String email, final String password) {
        dataReference.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                postEvent(LoginEvent.onSignUpSuccess);
                SignIn(email, password);

            }

            @Override
            public void onError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignUpError, firebaseError.getMessage());

            }
        });


    }

    @Override
    public void SignIn(String email, String password) {

        dataReference.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                initSignIn();

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignInError, firebaseError.getMessage());

            }
        });
       // postEvent(LoginEvent.onSignInSuccess);
    }

    private void initSignIn() {
        myUserReference = helper.getMyUserReference();
        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currenUser = dataSnapshot.getValue(User.class);

                if(currenUser == null){
                    registerNewUser();
                }
                helper.changeUserConnectionStatus(User.ONLINE);
                postEvent(LoginEvent.onSignInSuccess);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void registerNewUser() {
        User currenUser;
        String email = helper.getAuthUserEmail();
        if (email != null){
            currenUser = new User();
            currenUser.setEmail(email);
            myUserReference.setValue(currenUser);
        }
    }

    @Override
    public void checkSession() {
        if(dataReference.getAuth() != null){
            initSignIn();

        }else {
            postEvent(LoginEvent.onFailedRecoverSession);
        }

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
