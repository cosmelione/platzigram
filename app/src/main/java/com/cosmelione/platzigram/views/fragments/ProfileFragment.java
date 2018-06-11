package com.cosmelione.platzigram.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosmelione.platzigram.R;
import com.cosmelione.platzigram.adapter.PictureCardAdapter;
import com.cosmelione.platzigram.model.Picture;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Picture> pictureList;


    public ProfileFragment() {
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
        pictureList = new ArrayList<>();
        String title = "Futuro";
        String description = "I don't have the best feet.";
        String imagePath = "http://www.superstarfeet.com/wp-content/uploads/2016/11/ali4.png";
        Picture p = new Picture(imagePath, "Alicia Vikander", Calendar.getInstance(), 9, title, description);
        pictureList.add(p);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        mRecyclerView = rootView.findViewById(R.id.recycler_cards);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mLayoutManager = gridLayoutManager;
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PictureCardAdapter(pictureList, R.layout.cardview_picture, getParentFragment());
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
