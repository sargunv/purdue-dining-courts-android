package me.sargunvohra.android.purduediningcourts.model.retail;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import me.sargunvohra.android.purduediningcourts.model.Hours;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class RetailDay {

    String Name;
    Integer DayOfWeek;
    List<Hours> Hours;
}