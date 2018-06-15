package com.cosmelione.platzigram.login.interactor;

import com.cosmelione.platzigram.login.presenter.SignUpPresenter;
import com.cosmelione.platzigram.login.repository.SignUpRepository;
import com.cosmelione.platzigram.login.repository.SignUpRepositoryImpl;

public class SignUpInteractorImpl implements SignUpInteractor {

    private SignUpPresenter signUpPresenter;
    private SignUpRepository signUpRepository;

    public SignUpInteractorImpl(SignUpPresenter signUpPresenter) {
        this.signUpPresenter = signUpPresenter;
        this.signUpRepository = new SignUpRepositoryImpl(this.signUpPresenter);
    }

    @Override
    public void signUp(String email, String password, String name, String username) {
        signUpRepository.signUp(email, password, name, username);
    }

}
