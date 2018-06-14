package com.cosmelione.platzigram.post.view;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosmelione.platzigram.R;
import com.cosmelione.platzigram.adapter.PictureCardAdapter;
import com.cosmelione.platzigram.model.Picture;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private static final int REQUEST_CAMERA = 1;
    private FloatingActionButton fabCamera;
    private String mCurrentPhotoPath;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Picture> pictureList;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.

        initDataset();
    }

    private void initDataset() {
        pictureList = new ArrayList<Picture>();
        String title = "Futuro";
        String description = "dhsdhsdhdshdhshdhdhsd sddhsshsdh dhsddhshhhhhhhhhh sdsudssdudusududuu usdsudssdsuudsdususd";
        Picture p = new Picture("https://pics.wikifeet.com/Alison-Brie-Feet-131712.jpg", "Alberto Garcia", Calendar.getInstance(), 9, title, description);
        pictureList.add(p);
        pictureList.add(p);
        pictureList.add(p);
        pictureList.add(p);
        pictureList.add(p);
        pictureList.add(p);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //Config toolbar
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.home_bottom_nav);

        //FAB
        fabCamera = rootView.findViewById(R.id.fab_camera);
        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });


        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = rootView.findViewById(R.id.recycler_cards);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager = linearLayoutManager;
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PictureCardAdapter(pictureList, R.layout.cardview_picture, getParentFragment());
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    private void takePicture() {
        Intent intentTakePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentTakePicture.resolveActivity(getActivity().getPackageManager()) != null) {

            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.cosmelione.platzigram",
                        photoFile);
                intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intentTakePicture, REQUEST_CAMERA);
            }

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == getActivity().RESULT_OK) {

            Intent intentPostPicture = new Intent(getContext(), PostPictureActivity.class);
            intentPostPicture.putExtra(PostPictureActivity.CURRENT_PHOTO, mCurrentPhotoPath);
            startActivity(intentPostPicture);

//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            mImageView.setImageBitmap(imageBitmap);

        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

}
