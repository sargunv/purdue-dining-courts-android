package me.sargunvohra.android.purduediningcourts.model;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class Hours {

    String StartTime;
    String EndTime;
}
