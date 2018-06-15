package com.cosmelione.platzigram.login.presenter;

import com.cosmelione.platzigram.login.interactor.SignUpInteractor;
import com.cosmelione.platzigram.login.interactor.SignUpInteractorImpl;
import com.cosmelione.platzigram.login.view.CreateAccountActivityView;

public class SignUpPresenterImpl implements SignUpPresenter {

    private CreateAccountActivityView createAccountActivityView;
    private SignUpInteractor signUpInteractor;

    public SignUpPresenterImpl(CreateAccountActivityView createAccountActivityView) {
        this.createAccountActivityView = createAccountActivityView;
        this.signUpInteractor = new SignUpInteractorImpl(this);
    }

    @Override
    public void signUp(String email, String password, String name, String username) {
        signUpInteractor.signUp(email, password, name, username);
    }

    @Override
    public void createAccountSuccess() {
        createAccountActivityView.createAccountSuccess();
    }

    @Override
    public void createAccountError() {
        createAccountActivityView.createAccountError();
    }


}
