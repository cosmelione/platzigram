package com.cosmelione.platzigram.login.view;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.cosmelione.platzigram.R;
import com.cosmelione.platzigram.login.presenter.LoginPresenter;
import com.cosmelione.platzigram.login.presenter.LoginPresenterImpl;
import com.cosmelione.platzigram.views.ContainerActivity;
import com.cosmelione.platzigram.views.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity implements LoginActivityView {

    private TextInputEditText username;
    private TextInputEditText password;
    private Button buttonLogin;
    private ProgressBar progressBarLogin;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.button_login);
        progressBarLogin = findViewById(R.id.progressbar_login);

        loginPresenter = new LoginPresenterImpl(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.signIn(username.getText().toString(), password.getText().toString());
            }
        });

    }

    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        buttonLogin.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        buttonLogin.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressBarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarLogin.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {

    }

    @Override
    public void showCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToWebsite(View view) {
        Uri web = Uri.parse("http://juansopale.es");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(web);
        startActivity(intent);
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginError() {

    }
}
