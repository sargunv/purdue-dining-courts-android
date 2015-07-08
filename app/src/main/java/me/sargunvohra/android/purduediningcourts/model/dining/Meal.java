package me.sargunvohra.android.purduediningcourts.model.dining;

import android.support.annotation.Nullable;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import me.sargunvohra.android.purduediningcourts.model.Hours;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class Meal {

    String Name;
    Integer Order;
    String Status;
    Hours Hours;

    @Nullable
    List<Station> Stations;
}
