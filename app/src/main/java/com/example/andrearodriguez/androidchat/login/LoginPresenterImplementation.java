package com.example.andrearodriguez.androidchat.login;

/**
 * Created by andrearodriguez on 6/9/16.
 */
public class LoginPresenterImplementation implements LogingPresenter{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImplementation(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor =new LoginInteractorImpl();
    }

    @Override
    public void onDestroy() {
        loginView = null;

    }

    @Override
    public void checkForAuthenticationUser() {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.checkSession();

    }

    @Override
    public void validateLogin(String email, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email, password);

    }

    @Override
    public void registerNewUser(String email, String password) {

        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignUp(email, password);
    }

    private void onSignInSucces(){
        if(loginView != null){
            loginView.navigationToMainScreen();

        }

    }
    private void onSignUpSucces(){
        if(loginView != null){
            loginView.newUserSuccess();

        }

    }
    private void onSignInError(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);

        }

    }
    private void onSignUpError(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }

    }
}
