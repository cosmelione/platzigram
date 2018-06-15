package com.cosmelione.platzigram.login.view;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public interface LoginActivityView {

    void enableInputs();
    void disableInputs();
    void showProgressBar();
    void hideProgressBar();
    void loginSuccess();
    void loginError(String error);
    void showCreateAccount();
    void  goToSignInWithGoogle(GoogleSignInClient googleSignInClient);

    void goToWebsite();

}
