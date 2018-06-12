package com.cosmelione.platzigram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cosmelione.platzigram.views.ContainerActivity;
import com.cosmelione.platzigram.login.view.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        boolean login = false;

        String s = String.format(getString(R.string.timeAgoCard),10);
        System.out.println(s);

        Intent intent;

        if (login) {
             intent = new Intent(this, ContainerActivity.class);

        }
        else {
            intent = new Intent(this, LoginActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
