package com.cosmelione.platzigram.login.repository;

import com.cosmelione.platzigram.login.presenter.LoginPresenter;

public class LoginRepositoryImpl implements LoginRepository {

    private LoginPresenter loginPresenter;

    public LoginRepositoryImpl(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void signIn(String username, String password) {

        boolean success = true;

        if (success) {
            loginPresenter.loginSuccess();
        }

        else {
            loginPresenter.loginError("Error");
        }
    }
}
