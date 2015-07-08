package me.sargunvohra.android.purduediningcourts.model.retail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class RetailLocations {

    @SerializedName("Location")
    List<RetailLocation> Locations;
}
