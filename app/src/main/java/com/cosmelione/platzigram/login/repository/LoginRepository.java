package com.cosmelione.platzigram.login.repository;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface LoginRepository {
    void signIn(String username, String password);
    void signInAuthWithGoogle(GoogleSignInAccount account);
}
