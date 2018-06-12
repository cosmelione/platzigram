package com.cosmelione.platzigram.login.view;

import android.view.View;

public interface LoginActivityView {

    void enableInputs();
    void disableInputs();
    void showProgressBar();
    void hideProgressBar();
    void loginSuccess();
    void loginError(String error);
    void showCreateAccount();
    void goToWebsite();

}
