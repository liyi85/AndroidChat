package com.example.andrearodriguez.androidchat.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.andrearodriguez.androidchat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txtEmail)
    EditText txtEmail;
    @BindView(R.id.txtPassword)
    EditText txtPassword;
    @BindView(R.id.btnSignin)
    Button btnSignin;
    @BindView(R.id.signInUp)
    Button signInUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnSignin, R.id.signInUp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignin:
                handleSignin();
                break;
            case R.id.signInUp:
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
}
