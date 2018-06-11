package com.cosmelione.platzigram.views.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
public class HomeFragment extends Fragment {



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


//    private int getSelectedItem(BottomNavigationView bottomNavigationView){
//        Menu menu = bottomNavigationView.getMenu();
//        for (int i=0;i<bottomNavigationView.getMenu().size();i++){
//            MenuItem menuItem = menu.getItem(i);
//            if (menuItem.isChecked()){
//                return menuItem.getItemId();
//            }
//        }
//        return 0;
//    }
//
//    private String getTag (int id) {
//        switch (id) {
//            case R.id.home_menu_item:
//                return getString(R.string.HomeFragment);
//            case R.id.search_menu_item:
//                return getString(R.string.SearchFragment);
//            case R.id.profile_menu_item:
//                return getString(R.string.ProfileFragment);
//            default:
//                return "";
//        }
//    }



}
