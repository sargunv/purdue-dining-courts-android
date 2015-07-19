package me.sargunvohra.android.purduediningcourts.model.dining.item;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import lombok.Data;

@ParcelablePlease
@Data
public class NutritionFact implements Parcelable, Comparable<NutritionFact> {

    String Name;
    Integer Ordinal;

    @Nullable
    Double Value;

    @Nullable
    String LabelValue;

    @Nullable
    String DailyValue;
    public static final Creator<NutritionFact> CREATOR = new Creator<NutritionFact>() {
        public NutritionFact createFromParcel(Parcel source) {
            NutritionFact target = new NutritionFact();
            NutritionFactParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public NutritionFact[] newArray(int size) {
            return new NutritionFact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        NutritionFactParcelablePlease.writeToParcel(this, dest, flags);
    }

    @Override
    public int compareTo(NutritionFact other) {
        return getOrdinal().compareTo(other.getOrdinal());
    }
}
