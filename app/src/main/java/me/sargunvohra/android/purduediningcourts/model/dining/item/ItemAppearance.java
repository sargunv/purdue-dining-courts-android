package me.sargunvohra.android.purduediningcourts.model.dining.item;

import java.util.Date;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class ItemAppearance {

    Date Date;
    String Location;
    String Meal;
    String Station;
}
