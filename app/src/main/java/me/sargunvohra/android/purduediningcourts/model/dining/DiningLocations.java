package me.sargunvohra.android.purduediningcourts.model.dining;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class DiningLocations {

    @SerializedName("Location")
    List<DiningLocation> Locations;
}
