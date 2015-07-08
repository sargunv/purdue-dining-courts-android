package me.sargunvohra.android.purduediningcourts.model.dining;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class DayMenu {

    String Location;
    String Date;
    String Notes;
    List<Meal> Meals;
}
