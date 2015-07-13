package me.sargunvohra.android.purduediningcourts.model.dining;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.List;

import lombok.Data;

@ParcelablePlease
@Data
public class DiningLocations implements Parcelable {

    @SerializedName("Location")
    List<DiningLocation> Locations;
    public static final Creator<DiningLocations> CREATOR = new Creator<DiningLocations>() {
        public DiningLocations createFromParcel(Parcel source) {
            DiningLocations target = new DiningLocations();
            DiningLocationsParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public DiningLocations[] newArray(int size) {
            return new DiningLocations[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        DiningLocationsParcelablePlease.writeToParcel(this, dest, flags);
    }
}
