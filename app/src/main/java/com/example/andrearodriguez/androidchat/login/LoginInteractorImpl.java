package com.example.andrearodriguez.androidchat.login;

/**
 * Created by andrearodriguez on 6/11/16.
 */
public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImpl() {

        loginRepository = new LoginRepositoryImpl();
    }



    @Override
    public void checkSession() {
        loginRepository.checkSession();

    }

    @Override
    public void doSignUp(String email, String password) {
        loginRepository.signUp(email, password);

    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.SignIn(email, password);

    }
}
