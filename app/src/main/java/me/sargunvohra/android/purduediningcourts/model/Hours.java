package me.sargunvohra.android.purduediningcourts.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import lombok.Data;

@Data
@ParcelablePlease
public class Hours implements Parcelable {

    String StartTime;
    String EndTime;
    public static final Creator<Hours> CREATOR = new Creator<Hours>() {
        public Hours createFromParcel(Parcel source) {
            Hours target = new Hours();
            HoursParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Hours[] newArray(int size) {
            return new Hours[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        HoursParcelablePlease.writeToParcel(this, dest, flags);
    }
}
