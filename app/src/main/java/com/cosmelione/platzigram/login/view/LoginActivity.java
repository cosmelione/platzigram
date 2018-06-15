package com.cosmelione.platzigram.login.view;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmelione.platzigram.R;
import com.cosmelione.platzigram.login.presenter.LoginPresenter;
import com.cosmelione.platzigram.login.presenter.LoginPresenterImpl;
import com.cosmelione.platzigram.views.ContainerActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity implements LoginActivityView {

    private static final int RC_SIGN_IN = 9001;

    private ImageView imageLogo;
    private TextInputEditText username;
    private TextInputEditText password;
    private Button buttonLogin;
    private ProgressBar progressBarLogin;
    private TextView textLinkRegister;
    private LoginPresenter loginPresenter;
    private SignInButton signInButtonWithGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imageLogo = findViewById(R.id.imageLogo);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.button_login);
        progressBarLogin = findViewById(R.id.progressbar_login);
        textLinkRegister = findViewById(R.id.textLinkRegister);
        signInButtonWithGoogle = findViewById(R.id.sign_in_with_google);

        loginPresenter = new LoginPresenterImpl(this);

        imageLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWebsite();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.signIn(username.getText().toString(), password.getText().toString());
            }
        });

        signInButtonWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.signInWithGoogle();
            }
        });

        textLinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateAccount();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            loginPresenter.signIn(data);
        }
    }

    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        buttonLogin.setEnabled(true);
        signInButtonWithGoogle.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        buttonLogin.setEnabled(false);
        signInButtonWithGoogle.setEnabled(false);
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
    public void loginSuccess() {
        Intent intentHome = new Intent(this, ContainerActivity.class);
        startActivity(intentHome);
        finish();
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this, String.format(getString(R.string.login_error), error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void  goToSignInWithGoogle(@NonNull GoogleSignInClient googleSignInClient) {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void goToWebsite() {
        Uri web = Uri.parse("http://juansopale.es");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(web);
        startActivity(intent);
    }


}
