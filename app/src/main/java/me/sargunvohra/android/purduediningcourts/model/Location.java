package me.sargunvohra.android.purduediningcourts.model;

@SuppressWarnings("unused")
public interface Location {
    String getName();
    Address getAddress();
    String getPhoneNumber();
    double getLatitude();
    double getLongitude();
}
