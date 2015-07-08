package me.sargunvohra.android.purduediningcourts.model;

import android.support.annotation.Nullable;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class Address {

    String Street;
    String City;
    String State;
    String ZipCode;

    @Nullable
    String Country;

    @Nullable
    String CountryCode;
}
