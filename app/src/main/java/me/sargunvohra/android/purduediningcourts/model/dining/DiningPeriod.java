package me.sargunvohra.android.purduediningcourts.model.dining;

import java.util.Date;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningDay;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class DiningPeriod {

    String Id;
    String Name;
    Date EffectiveDate;
    List<DiningDay> Days;
}
