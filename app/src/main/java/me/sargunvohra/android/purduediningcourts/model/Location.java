package me.sargunvohra.android.purduediningcourts.model;

import android.os.Parcelable;

public interface Location extends Parcelable {
    String getName();

    String getFullName();

    Address getAddress();

    String getPhoneNumber();

    double getLatitude();

    double getLongitude();

    String getTileImage();
}
