package com.cosmelione.platzigram.login.view;

import android.view.View;

public interface LoginActivityView {

    void enableInputs();
    void disableInputs();
    void showProgressBar();
    void hideProgressBar();
    void loginError(String error);
    void showCreateAccount(View view);
    void goToWebsite(View view);
    void loginSuccess();
    void loginError();
}
