package com.cosmelione.platzigram.login.repository;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.cosmelione.platzigram.login.presenter.LoginPresenter;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginRepositoryImpl implements LoginRepository {

    private LoginPresenter loginPresenter;
    private FirebaseAuth firebaseAuth;


    public LoginRepositoryImpl(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void signIn(String username, String password) {

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    loginPresenter.loginSuccess();
                }
                else {
                    loginPresenter.loginError("Error");
                }
            }
        });

    }

    @Override
    public void signInAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) loginPresenter.getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            loginPresenter.loginSuccess();
                        } else {
                            // If sign in fails, display a message to the user.
                            loginPresenter.loginError("Fallo de autenticación por Google");
                        }
                    }
                });

    }

    @Override
    public void signOut() {
        // Firebase sign out
        firebaseAuth.signOut();

    }


}
