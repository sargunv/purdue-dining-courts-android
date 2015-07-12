package me.sargunvohra.android.purduediningcourts.model;

public interface Location {
    String getName();

    String getFullName();

    Address getAddress();

    String getPhoneNumber();

    double getLatitude();

    double getLongitude();

    String getTileImage();
}
