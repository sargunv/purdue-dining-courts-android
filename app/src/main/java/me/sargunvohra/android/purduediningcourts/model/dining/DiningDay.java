package me.sargunvohra.android.purduediningcourts.model.dining;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import me.sargunvohra.android.purduediningcourts.model.Hours;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class DiningDay {

    String Name;
    Integer DayOfWeek;
    List<Meal> Meals;
    Hours Hours;
}
