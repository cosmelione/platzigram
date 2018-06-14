package com.cosmelione.platzigram.login.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.cosmelione.platzigram.R;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        System.out.println(getString(R.string.galery));
//        CardView cv = (CardView) findViewById(R.id.pictureCardview);
//        cv.findViewById(R.id.pictureCardview);
//        iv.setImageResource(R.drawable.female_lion);
//        getSupportActionBar().
//        Intent intent = getIntent();
//        intent.setAction(Intent.ACTION_CHOOSER);
    }
}
