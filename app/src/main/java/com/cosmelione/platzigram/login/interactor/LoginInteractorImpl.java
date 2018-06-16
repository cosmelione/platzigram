package com.cosmelione.platzigram.login.interactor;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.cosmelione.platzigram.R;
import com.cosmelione.platzigram.login.presenter.LoginPresenter;
import com.cosmelione.platzigram.login.repository.LoginRepository;
import com.cosmelione.platzigram.login.repository.LoginRepositoryImpl;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LoginInteractorImpl implements LoginInteractor {

    private static final String TAG = "GoogleActivity";

    private LoginPresenter loginPresenter;
    private LoginRepository loginRepository;
    private GoogleSignInClient googleSignInClient;

    public LoginInteractorImpl(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
        this.loginRepository = new LoginRepositoryImpl(this.loginPresenter);

        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(loginPresenter.getContext().getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(loginPresenter.getContext(), gso);
        // [END config_signin]

    }

    @Override
    public void signIn(String username, String password) {
        loginRepository.signIn(username, password);
    }

    @Override
    public void signInWithGoogle() {
        if (googleSignInClient != null) {
            loginPresenter.goToSignInwithGoogle(googleSignInClient);
        }
    }

    @Override
    public void signIn(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = task.getResult(ApiException.class);
            loginRepository.signInAuthWithGoogle(account);
            Log.i(TAG, "Google Well");
        } catch (ApiException e) {
            // Google Sign In failed, update UI appropriately
            Log.w(TAG, "Google sign in failed", e);
            loginPresenter.loginError("Google sign in failed");
        }
    }

    @Override
    public void signOut() {
        loginRepository.signOut();
        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener((Activity)loginPresenter.getContext(),
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        loginPresenter.goBackToLogin();
                    }
                });
    }

}
