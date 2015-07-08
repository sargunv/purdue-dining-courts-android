package me.sargunvohra.android.purduediningcourts.model.dining.item;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class Allergen {

    String Name;
    Boolean Value;
}
