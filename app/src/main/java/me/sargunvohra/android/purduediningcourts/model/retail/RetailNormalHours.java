package me.sargunvohra.android.purduediningcourts.model.retail;

import android.support.annotation.Nullable;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningDay;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class RetailNormalHours {

    List<RetailDay> Days;

    @Nullable
    RetailPeriod Period;
}
