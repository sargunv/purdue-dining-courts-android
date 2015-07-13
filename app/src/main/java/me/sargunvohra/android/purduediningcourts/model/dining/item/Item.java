package me.sargunvohra.android.purduediningcourts.model.dining.item;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.List;

import lombok.Data;

@ParcelablePlease
@Data
public class Item implements Parcelable {

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
    public static final Creator<Item> CREATOR = new Creator<Item>() {
        public Item createFromParcel(Parcel source) {
            Item target = new Item();
            ItemParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ItemParcelablePlease.writeToParcel(this, dest, flags);
    }
}
