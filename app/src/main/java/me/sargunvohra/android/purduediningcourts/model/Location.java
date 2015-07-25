package me.sargunvohra.android.purduediningcourts.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

public interface Location extends Parcelable {
    String getName();

    String getFullName();

    Address getAddress();

    String getPhoneNumber();

    double getLatitude();

    double getLongitude();

    String getTileImage();

    String getTimings(boolean twentyFourHourFormat);

    @Nullable
    Day getToday();
}
