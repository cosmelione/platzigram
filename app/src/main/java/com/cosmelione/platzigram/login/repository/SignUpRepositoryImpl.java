package com.cosmelione.platzigram.login.repository;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.cosmelione.platzigram.login.interactor.SignUpInteractor;
import com.cosmelione.platzigram.login.presenter.SignUpPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpRepositoryImpl implements SignUpRepository {

    private SignUpPresenter signUpPresenter;
    private FirebaseAuth firebaseAuth;

    public SignUpRepositoryImpl(SignUpPresenter signUpPresenter) {
        this.signUpPresenter = signUpPresenter;

        firebaseAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void signUp(String email, String password, String name, String username) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            signUpPresenter.createAccountSuccess();
                        }
                        else {
                            signUpPresenter.createAccountError();
                        }
                    }
                });

    }


}
