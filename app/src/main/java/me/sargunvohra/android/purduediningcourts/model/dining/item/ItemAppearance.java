package me.sargunvohra.android.purduediningcourts.model.dining.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.Date;

import lombok.Data;

@ParcelablePlease
@Data
public class ItemAppearance implements Parcelable {

    Date Date;
    String Location;
    String Meal;
    String Station;
    public static final Creator<ItemAppearance> CREATOR = new Creator<ItemAppearance>() {
        public ItemAppearance createFromParcel(Parcel source) {
            ItemAppearance target = new ItemAppearance();
            ItemAppearanceParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public ItemAppearance[] newArray(int size) {
            return new ItemAppearance[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ItemAppearanceParcelablePlease.writeToParcel(this, dest, flags);
    }
}
