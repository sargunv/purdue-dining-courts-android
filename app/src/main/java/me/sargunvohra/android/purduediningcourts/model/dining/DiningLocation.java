package me.sargunvohra.android.purduediningcourts.model.dining;

import android.support.annotation.Nullable;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import me.sargunvohra.android.purduediningcourts.model.Address;
import me.sargunvohra.android.purduediningcourts.model.Location;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class DiningLocation implements Location {

    String Name;
    Address Address;
    String PhoneNumber;
    double Latitude;
    double Longitude;
    List<DiningPeriod> NormalHours;
    List<String> Images;
    String FormalName;
}
