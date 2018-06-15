package com.cosmelione.platzigram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cosmelione.platzigram.views.ContainerActivity;
import com.cosmelione.platzigram.login.view.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        boolean login = false;
//
//        String s = String.format(getString(R.string.timeAgoCard),10);
//        System.out.println(s);

        firebaseAuth = FirebaseAuth.getInstance();


        Intent intent;

        if (firebaseAuth.getCurrentUser() != null) {
             intent = new Intent(this, ContainerActivity.class);
        }
        else {
            intent = new Intent(this, LoginActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
