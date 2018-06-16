package com.cosmelione.platzigram.login.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.cosmelione.platzigram.login.interactor.LoginInteractor;
import com.cosmelione.platzigram.login.interactor.LoginInteractorImpl;
import com.cosmelione.platzigram.login.view.LoginActivityView;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginActivityView loginActivityView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginActivityView loginActivityView) {
        this.loginActivityView = loginActivityView;
        this.loginInteractor = new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String username, String password) {
        loginActivityView.disableInputs();
        loginActivityView.showProgressBar();
        loginInteractor.signIn(username, password);
    }

    @Override
    public void signIn(Intent data) {
        loginActivityView.disableInputs();
        loginActivityView.showProgressBar();
        loginInteractor.signIn(data);
    }

    @Override
    public void loginSuccess() {
        loginActivityView.hideProgressBar();
        loginActivityView.loginSuccess();
    }

    @Override
    public void loginError(String error) {
        loginActivityView.enableInputs();
        loginActivityView.hideProgressBar();
        loginActivityView.loginError(error);
    }

    @Override
    public void signInWithGoogle() {
        loginInteractor.signInWithGoogle();
    }

    @Override
    public void goToSignInwithGoogle(GoogleSignInClient googleSignInClient) {
        loginActivityView.goToSignInWithGoogle(googleSignInClient);
    }

    @Override
    public Context getContext() {
        return ((Activity) loginActivityView);
    }

    @Override
    public void goBackToLogin() {
        loginActivityView.goBackToLogin();
    }

    @Override
    public void signOut() {
        loginInteractor.signOut();
    }

//    @Override
//    public Context getContext() {
//        return (Context) this.loginActivityView;
//    }
}
