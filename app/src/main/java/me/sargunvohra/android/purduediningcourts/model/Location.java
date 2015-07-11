package me.sargunvohra.android.purduediningcourts.model;

@SuppressWarnings("unused")
public interface Location {
    String getName();
    String getFullName();
    Address getAddress();
    String getPhoneNumber();
    double getLatitude();
    double getLongitude();
    String getImageId();
}
