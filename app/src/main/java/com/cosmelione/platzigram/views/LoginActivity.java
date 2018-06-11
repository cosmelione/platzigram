package com.cosmelione.platzigram.views;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cosmelione.platzigram.R;
import com.cosmelione.platzigram.views.ContainerActivity;
import com.cosmelione.platzigram.views.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }

    public void showCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
//        finish();
    }

    public void goToWebsite(View view) {
        Uri web = Uri.parse("http://juansopale.es");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(web);
        startActivity(intent);
    }
}
