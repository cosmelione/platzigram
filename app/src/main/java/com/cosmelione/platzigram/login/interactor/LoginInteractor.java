package com.cosmelione.platzigram.login.interactor;

import android.content.Intent;

public interface LoginInteractor {
    void signIn(String username, String password);
    void signInWithGoogle();
    void signIn(Intent data);

    void signOut();
}
