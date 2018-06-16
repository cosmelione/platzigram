package com.cosmelione.platzigram.post.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cosmelione.platzigram.R;

public class PostPictureActivity extends AppCompatActivity {

    protected static final String CURRENT_PHOTO ="CURRENT_PHOTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_picture);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.photo_details_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        TextInputEditText title = findViewById(R.id.title_post_picture);
//        TextInputEditText description = findViewById(R.id.description_post_picture);

        Button cancelButton = findViewById(R.id.cancel_button);
//        Button postButton = findViewById(R.id.post_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelPostPicture();
            }
        });

        Intent intentFrom = getIntent();
        Bundle extras = intentFrom.getExtras();

//        Bitmap imageBitmap = (Bitmap) extras.get();
        if (extras != null) {
//            Uri imageUri = Uri.parse(extras.getString(CURRENT_PHOTO));
//            try {
//                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
//
//                imagePostPicture = findViewById(R.id.image_post_picture);
//                imagePostPicture.setImageBitmap(imageBitmap);
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//            }
            String imagePath = extras.getString(CURRENT_PHOTO);
            ImageView imagePostPicture = findViewById(R.id.image_post_picture);
            Glide.with(this).load(imagePath).into(imagePostPicture);
        }

    }

    public void cancelPostPicture() {
        finish();
    }

    @Override
    public boolean onNavigateUp() {
        cancelPostPicture();
        return true;
    }
}
