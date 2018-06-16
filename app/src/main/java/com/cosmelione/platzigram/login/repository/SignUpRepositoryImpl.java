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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpRepositoryImpl implements SignUpRepository {

    private SignUpPresenter signUpPresenter;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    public SignUpRepositoryImpl(SignUpPresenter signUpPresenter) {
        this.signUpPresenter = signUpPresenter;

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }


    @Override
    public void signUp(String email, String password, String name, String username) {

        final Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("name",name);
        user.put("username",username);

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser().getUid())
                                    .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        signUpPresenter.createAccountSuccess();
                                    }

                                    else {
                                        signUpPresenter.createAccountError();
                                    }
                                }
                            });

                        }
                        else {
                            signUpPresenter.createAccountError();
                        }
                    }
                });

    }


}
