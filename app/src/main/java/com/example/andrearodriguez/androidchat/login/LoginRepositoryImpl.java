package com.example.andrearodriguez.androidchat.login;

import android.util.Log;

import com.example.andrearodriguez.androidchat.domain.FirebaseHelper;

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
        Log.e("LoginRepositoryImpl", "signUp");

    }

    @Override
    public void SignIn(String email, String password) {

        Log.e("LoginRepositoryImpl", "SignIn");
    }

    @Override
    public void checkSession() {
        Log.e("LoginRepositoryImpl", "checkSession");

    }
}
