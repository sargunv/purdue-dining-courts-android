package me.sargunvohra.android.purduediningcourts.model.retail;

import android.support.annotation.Nullable;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import me.sargunvohra.android.purduediningcourts.model.Address;
import me.sargunvohra.android.purduediningcourts.model.Location;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class RetailLocation implements Location {

    String Name;
    Address Address;
    String PhoneNumber;
    double Latitude;
    double Longitude;
    RetailNormalHours NormalHours;
    String Logo;
    String Banner;
    String Type;
    String Description;
    String DescriptionUrl;

    @Nullable
    String MenuUrl;

    @Override
    public String getFullName() {
        return getName();
    }

    @Override
    public String getImageId() {
        return getLogo();
    }
}
