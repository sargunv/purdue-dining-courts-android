package me.sargunvohra.android.purduediningcourts.model.dining;

import android.support.annotation.Nullable;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import me.sargunvohra.android.purduediningcourts.model.Address;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class DiningLocation {

    String Name;
    Address Address;
    String PhoneNumber;
    double Latitude;
    double Longitude;
    List<DiningPeriod> NormalHours;
    List<String> Images;
    String FormalName;
}
