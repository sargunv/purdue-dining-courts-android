package me.sargunvohra.android.purduediningcourts.model.dining.item;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class ItemSchedule {

    @SerializedName("ItemAppearance")
    List<ItemAppearance> ItemAppearances;
}
