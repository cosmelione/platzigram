package com.cosmelione.platzigram.views.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cosmelione.platzigram.R;
import com.cosmelione.platzigram.model.Picture;
import com.cosmelione.platzigram.views.ContainerActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhotoDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotoDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PICTURE = "ID_PICTURE";

    // TODO: Rename and change types of parameters
    private Picture mPicture;


    public PhotoDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param p Picture Parameter.
     * @return A new instance of fragment PhotoDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotoDetailsFragment newInstance(Picture p) {
        PhotoDetailsFragment fragment = new PhotoDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PICTURE, p);  //(ARG_PICTURE, p);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPicture = getArguments().getParcelable(ARG_PICTURE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_photo_details, container, false);

        //Config toolbar
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.photo_details_title);

        ContainerActivity c = (ContainerActivity) getActivity();

        c.setSupportActionBar(toolbar);
        c.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView picturePhotoDetails = rootView.findViewById(R.id.picture_photo_details);
        TextView namePhotoDetails = rootView.findViewById(R.id.name_photo_details);
        TextView likesTextPhotoDetails = rootView.findViewById(R.id.likes_text_photo_details);
        TextView titlePhotoDetails = rootView.findViewById(R.id.title_photo_details);
        TextView descriptionPhotoDetails = rootView.findViewById(R.id.description_photo_details);

        Glide.with(getActivity()).load(mPicture.getImagePath()).into(picturePhotoDetails);
        namePhotoDetails.setText(mPicture.getUsername());
        likesTextPhotoDetails.setText(c.getLikesText(mPicture.getLikesNumber()));
        titlePhotoDetails.setText(mPicture.getTitle());
        descriptionPhotoDetails.setText(mPicture.getDescription());

        return rootView;
    }

}
