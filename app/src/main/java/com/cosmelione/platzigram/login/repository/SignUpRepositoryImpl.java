package com.cosmelione.platzigram.login.repository;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.cosmelione.platzigram.login.presenter.SignUpPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;



public class SignUpRepositoryImpl implements SignUpRepository {

    private final String TAG = "Evidence";

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

//        final SharedPreferences sp = signUpPresenter.getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        sp.edit().putString("email", email);
//        sp.edit().putString("name", name);
//        sp.edit().putString("username", username);


        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener((Activity) signUpPresenter.getContext(), new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        firestoreAddUser(user);
                    }
                })
                .addOnFailureListener((Activity) signUpPresenter.getContext(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        signUpPresenter.createAccountError();
                        Log.w(TAG, "No way create fireauth", e);
                    }
                });



    }

    public void firestoreAddUser(Map<String, Object> user ) {

        firebaseFirestore.collection("users").document("usa1").set(user)
                .addOnSuccessListener((Activity) signUpPresenter.getContext(), new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        signUpPresenter.createAccountSuccess();
                        Log.w(TAG, "Hopes with Firestore");
                    }
                })
                .addOnFailureListener((Activity) signUpPresenter.getContext(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        signUpPresenter.createAccountError();
                        Log.w(TAG, "No way with Firestore",e);
                    }
                });
//                .addOnCompleteListener((Activity) signUpPresenter.getContext(),
//                        new OnCompleteListener<DocumentReference>() {
//                            @Override
//                            public void onComplete(@NonNull Task<DocumentReference> task) {
//                                if (task.isSuccessful()) {
//                                    signUpPresenter.createAccountSuccess();
//                                    Log.w(TAG, "Hopes with Firestore");
//                                }
//                                else {
//                                    signUpPresenter.createAccountError();
//                                    Log.w(TAG, "No way with Firestore");
//                                }
//                            }
//                        });


    }


}
