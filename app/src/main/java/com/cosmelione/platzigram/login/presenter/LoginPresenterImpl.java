package com.cosmelione.platzigram.login.presenter;

import com.cosmelione.platzigram.login.interactor.LoginInteractor;
import com.cosmelione.platzigram.login.interactor.LoginInteractorImpl;
import com.cosmelione.platzigram.login.view.LoginActivityView;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginActivityView loginActivityView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginActivityView loginActivityView) {
        this.loginActivityView = loginActivityView;
        this.loginInteractor = new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String username, String password) {
        loginInteractor.signIn(username, password);
    }

    @Override
    public void loginSuccess() {
        loginActivityView.loginSuccess();
    }

    @Override
    public void loginError() {
        loginActivityView.loginError();
    }
}
