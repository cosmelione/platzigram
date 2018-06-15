package com.cosmelione.platzigram.login.view;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cosmelione.platzigram.R;
import com.cosmelione.platzigram.login.presenter.SignUpPresenter;
import com.cosmelione.platzigram.login.presenter.SignUpPresenterImpl;

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountActivityView {


    private TextInputEditText name, email, username, password;
    private Button buttonLogin;
    private SignUpPresenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.button_login);

        signUpPresenter = new SignUpPresenterImpl(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpPresenter.signUp(email.getText().toString(), password.getText().toString(),
                        name.getText().toString(), username.getText().toString());
            }
        });


    }

    @Override
    public void createAccountSuccess() {
        Toast.makeText(this, "Se ha creado con éxito la cuenta", Toast.LENGTH_SHORT).show();

    }

    @Override
    public  void createAccountError() {
        Toast.makeText(this, "Error al crear cuenta", Toast.LENGTH_SHORT).show();
    }


}
