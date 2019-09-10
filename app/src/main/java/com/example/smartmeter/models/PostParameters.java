package com.example.smartmeter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PostParameters implements Parcelable{
    public  String modem_hex;
    public  String date_from;
    public  String date_to;

    public PostParameters(String modem_hex, String date_from, String date_to){
        this.date_from = date_from;
        this.date_to = date_to;
        this.modem_hex = modem_hex;
    }

    protected PostParameters(Parcel in) {
        modem_hex = in.readString();
        date_from = in.readString();
        date_to = in.readString();
    }

    public static final Creator<PostParameters> CREATOR = new Creator<PostParameters>() {
        @Override
        public PostParameters createFromParcel(Parcel in) {
            return new PostParameters(in);
        }

        @Override
        public PostParameters[] newArray(int size) {
            return new PostParameters[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(modem_hex);
        dest.writeString(date_from);
        dest.writeString(date_to);
    }
}
