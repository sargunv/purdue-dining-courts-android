package me.sargunvohra.android.purduediningcourts.model.dining.item;

import android.support.annotation.Nullable;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class Item {

    String ID;
    String Name;
    Boolean IsVegetarian;

    @Nullable
    String Ingredients;

    @Nullable
    List<Allergen> Allergens;

    @Nullable
    ItemSchedule ItemSchedule;

    @Nullable
    List<NutritionFact> Nutrition;
}
