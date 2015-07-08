package me.sargunvohra.android.purduediningcourts.model.retail;

import android.support.annotation.Nullable;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import me.sargunvohra.android.purduediningcourts.model.Address;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class RetailLocation {

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
}
