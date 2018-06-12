package com.cosmelione.platzigram.login.interactor;

import com.cosmelione.platzigram.login.presenter.LoginPresenter;

public class LoginInteractorImpl implements LoginInteractor {

    private LoginPresenter loginPresenter;

    public LoginInteractorImpl(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void signIn(String username, String password) {

    }
}
