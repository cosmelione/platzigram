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
        loginActivityView.disableInputs();
        loginActivityView.showProgressBar();
        loginInteractor.signIn(username, password);
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
}
