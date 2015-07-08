package me.sargunvohra.android.purduediningcourts.model.dining;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import me.sargunvohra.android.purduediningcourts.model.dining.item.Item;

@Value
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class SearchResults {

    List<Item> Results;
}
