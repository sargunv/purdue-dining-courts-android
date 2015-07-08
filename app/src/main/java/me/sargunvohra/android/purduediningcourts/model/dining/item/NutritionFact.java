package me.sargunvohra.android.purduediningcourts.model.dining.item;

import android.support.annotation.Nullable;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class NutritionFact {

    String Name;
    Integer Ordinal;

    @Nullable
    Double Value;

    @Nullable
    String LabelValue;

    @Nullable
    String DailyValue;
}
