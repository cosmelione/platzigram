package com.cosmelione.platzigram.login.interactor;

import com.cosmelione.platzigram.login.presenter.LoginPresenter;
import com.cosmelione.platzigram.login.repository.LoginRepository;
import com.cosmelione.platzigram.login.repository.LoginRepositoryImpl;

public class LoginInteractorImpl implements LoginInteractor {

    private LoginPresenter loginPresenter;
    private LoginRepository loginRepository;

    public LoginInteractorImpl(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
        this.loginRepository = new LoginRepositoryImpl(this.loginPresenter);
    }

    @Override
    public void signIn(String username, String password) {
        loginRepository.signIn(username, password);
    }
}
