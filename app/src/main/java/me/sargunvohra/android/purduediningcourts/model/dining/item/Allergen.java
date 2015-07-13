package me.sargunvohra.android.purduediningcourts.model.dining.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import lombok.Data;

@ParcelablePlease
@Data
public class Allergen implements Parcelable {

    String Name;
    Boolean Value;
    public static final Creator<Allergen> CREATOR = new Creator<Allergen>() {
        public Allergen createFromParcel(Parcel source) {
            Allergen target = new Allergen();
            AllergenParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Allergen[] newArray(int size) {
            return new Allergen[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        AllergenParcelablePlease.writeToParcel(this, dest, flags);
    }
}
