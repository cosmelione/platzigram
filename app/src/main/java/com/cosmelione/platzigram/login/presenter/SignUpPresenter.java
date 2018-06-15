package com.cosmelione.platzigram.login.presenter;

import android.content.Context;

public interface SignUpPresenter {

    void signUp(String email, String password, String name, String username);

    void createAccountSuccess();
    void createAccountError();
//    Context getContext();

}
