package me.sargunvohra.android.purduediningcourts.model.retail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.List;

import lombok.Data;

@Data
@ParcelablePlease
public class RetailLocations implements Parcelable {

    @SerializedName("Location")
    List<RetailLocation> Locations;
    public static final Creator<RetailLocations> CREATOR = new Creator<RetailLocations>() {
        public RetailLocations createFromParcel(Parcel source) {
            RetailLocations target = new RetailLocations();
            RetailLocationsParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public RetailLocations[] newArray(int size) {
            return new RetailLocations[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        RetailLocationsParcelablePlease.writeToParcel(this, dest, flags);
    }
}
