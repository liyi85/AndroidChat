package com.example.andrearodriguez.androidchat.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.contactslist.ui.ContactListActivity;
import com.example.andrearodriguez.androidchat.login.LoginPresenterImpl;
import com.example.andrearodriguez.androidchat.login.LogingPresenter;
import com.example.andrearodriguez.androidchat.signup.ui.ActivitySignUp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.txtEmail)
    EditText txtEmail;
    @BindView(R.id.txtPassword)
    EditText txtPassword;
    @BindView(R.id.btnSignin)
    Button btnSignin;
    @BindView(R.id.btnSignInUp)
    Button btnSignInUp;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layoutMainContainer)
    RelativeLayout Container;

    private LogingPresenter logingPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        logingPresenter = new LoginPresenterImpl(this);



    }

    @Override
    protected void onDestroy() {
        logingPresenter.onDestroy();
        super.onDestroy();
    }

    @OnClick({R.id.btnSignin, R.id.btnSignInUp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignin:
                handleSignin();
                break;
            case R.id.btnSignInUp:
                handleSingnInUp();
                break;
        }
    }

    private void handleSingnInUp() {

        Log.e("AndroidChatAppSignup", txtEmail.getText().toString());
    }

    private void handleSignin() {
        Log.e("AndroidChatAppSignin", txtEmail.getText().toString());
    }

    @Override
    protected void onPause() {
        logingPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        logingPresenter.onResumen();
        logingPresenter.checkForAuthenticationUser();
    }

    @Override
    public void enableInputs() {
        setInputs(true);

    }

    @Override
    public void disableInputs() {

        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @OnClick(R.id.btnSignInUp)
    @Override
    public void handleSignUp() {
//        logingPresenter.registerNewUser(txtEmail.getText().toString(),
//                            txtPassword.getText().toString());

        startActivity(new Intent(this, ActivitySignUp.class));
    }

    @OnClick(R.id.btnSignin)
    @Override
    public void handleSignIn() {
        logingPresenter.validateLogin(txtEmail.getText().toString(),
                            txtPassword.getText().toString());

    }

    @Override
    public void navigationToMainScreen() {
        Intent intent = new Intent(this, ContactListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity (intent);

    }

    @Override
    public void loginError(String error) {
        txtPassword.setText("");
        String msgError = String.format(getString(R.string.loging_error_messagge_signin, error));

        txtPassword.setError(msgError);

    }

    @Override
    public void newUserSuccess() {
        throw  new UnsupportedOperationException("Operacion no es valida desde la Actividad Login");

    }

    @Override
    public void newUserError(String error) {
        throw  new UnsupportedOperationException("Operacion no es valida desde la Actividad SignUp");
    }
    private void setInputs (boolean enabled){
        txtEmail.setEnabled(enabled);
        txtPassword.setEnabled(enabled);
        btnSignin.setEnabled(enabled);
        btnSignInUp.setEnabled(enabled);
    }
}
