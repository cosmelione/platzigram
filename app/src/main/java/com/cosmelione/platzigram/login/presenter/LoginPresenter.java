package com.cosmelione.platzigram.login.presenter;


import android.content.Context;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public interface LoginPresenter {
    void signIn(String username, String password);
    void signIn(Intent data);
    void loginSuccess();
    void loginError(String error);
    void signInWithGoogle();
    void goToSignInwithGoogle(GoogleSignInClient googleSignInClient);
    Context getContext();


    void goBackToLogin();

    void signOut();
}
