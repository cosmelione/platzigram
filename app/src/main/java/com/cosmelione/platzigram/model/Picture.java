package com.cosmelione.platzigram.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Calendar;

public class Picture implements Parcelable {

    private String imagePath;
    private String username;
    private Calendar time;
    private int likesNumber;

    private String title;
    private String description;


    public Picture(String imagePath, String username, Calendar time, int likesNumber, String title, String description) {
        this.imagePath = imagePath;
        this.username = username;
        this.time = time;
        this.likesNumber = likesNumber;
        this.title = title;
        this.description = description;
    }

    private Picture(Parcel in) {
        imagePath = in.readString();
        username = in.readString();
        likesNumber = in.readInt();
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    public String getImagePath() {
        return imagePath;
    }

    /*public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikesNumber() {
        return likesNumber;
    }

    /*public void setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
    }*/

    public int getTimeAgo() {
        Calendar today = Calendar.getInstance();
        long differenceMS = today.getTime().getTime() - time.getTime().getTime();
        long days = differenceMS / (1000 * 60 * 60 * 24);

        return (int) days;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getTime() {
        return time;
    }

//    public String getDateString() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
//        return sdf.format(time.getTime());
//    }
//
//    public void setTimeFromString(String formatDate) {
//
//        if (time != null) return;
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
//        try {
//            time = Calendar.getInstance();
//            time.setTime(sdf.parse(formatDate));
//        }
//        catch (Exception e) {
//        }
//    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imagePath);
        dest.writeString(username);
        dest.writeInt(likesNumber);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeSerializable(time);

    }
}
