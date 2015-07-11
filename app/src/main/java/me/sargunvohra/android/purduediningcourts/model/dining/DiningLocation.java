package me.sargunvohra.android.purduediningcourts.model.dining;

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

    @Override
    public String getFullName() {
        return getFormalName();
    }

    @Override
    public String getImageId() {
        if (getImages().size() == 0)
            return null;
        else
            return getImages().get(0);
    }
}
