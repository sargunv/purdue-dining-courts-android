package me.sargunvohra.android.diningcourts.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationResponse {
    @SerializedName("Location")
    List<Location> locations;
}
