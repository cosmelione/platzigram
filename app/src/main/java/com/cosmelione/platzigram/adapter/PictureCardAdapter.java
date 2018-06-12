package com.cosmelione.platzigram.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cosmelione.platzigram.R;
import com.cosmelione.platzigram.model.Picture;
import com.cosmelione.platzigram.views.ContainerActivity;
import com.cosmelione.platzigram.views.fragments.PhotoDetailsFragment;
import com.cosmelione.platzigram.views.fragments.RootFragment;

import java.util.List;

public class PictureCardAdapter extends RecyclerView.Adapter<PictureCardAdapter.ViewHolder> {

    private List<Picture> pictures;
    private int resourceCardviewID;
    private Fragment parentFragment;

    public PictureCardAdapter(List<Picture> pictures, int resourceCardviewID, Fragment parentFragment) {
        this.pictures = pictures;
        this.resourceCardviewID = resourceCardviewID;
        this.parentFragment = parentFragment;
    }

    @NonNull
    @Override
    public PictureCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View cardview = LayoutInflater.from(parent.getContext()).inflate(resourceCardviewID, parent, false);

        return new ViewHolder(cardview);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureCardAdapter.ViewHolder holder, int position) {
        ContainerActivity c = ((ContainerActivity)holder.itemView.getContext());
        final Picture picture = pictures.get(position);
        holder.usernameCard.setText(picture.getUsername());
        holder.likeNumberCard.setText(c.getLikesText(picture.getLikesNumber()));
        holder.timeAgoCard.setText(c.getTimeAgoText(picture.getTimeAgo()));
        Glide.with(c).load(picture.getImagePath()).into(holder.pictureCard);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(v.getContext(), CreateAccountActivity.class);
                v.getContext().startActivity(intent);*/
                if (v.getContext() instanceof ContainerActivity) {

                    Picture p = new Picture(picture.getImagePath(), picture.getUsername(), picture.getTime(),
                            picture.getLikesNumber(), picture.getTitle(), picture.getDescription());
//                    ((ContainerActivity)v.getContext()).changeFragment(PhotoDetailsFragment.newInstance(p), tag);
                    ((RootFragment)parentFragment).changeFragment(PhotoDetailsFragment.newInstance(p), parentFragment.getArguments());
                }
            }
        };

        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView pictureCard;
        private TextView usernameCard;
        private TextView timeAgoCard;
        private TextView likeNumberCard;

        public ViewHolder(View v) {
            super(v);
            pictureCard = v.findViewById(R.id.bgImageCardview);
            usernameCard = v.findViewById(R.id.textNameCard);
            timeAgoCard = v.findViewById(R.id.textTimeAgoCard);
            likeNumberCard = v.findViewById(R.id.likesTextCard);
        }

    }

//    private String getLikesText(int likes) {
//        return String.format(parentActivity.getString(R.string.likesTextCard), likes);
//    }
//
//    private String getTimeAgoText(int days) {
//        switch (days) {
//            case 0:
//                return parentActivity.getString(R.string.todayTextCard);
//            case 1:
//                return parentActivity.getString(R.string.yesterdayTextCard);
//            default:
//                return String.format(parentActivity.getString(R.string.timeAgoCard), days);
//        }
//    }
}
