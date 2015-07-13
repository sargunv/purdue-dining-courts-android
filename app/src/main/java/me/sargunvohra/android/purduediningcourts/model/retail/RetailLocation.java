package me.sargunvohra.android.purduediningcourts.model.retail;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import lombok.Data;
import me.sargunvohra.android.purduediningcourts.model.Address;
import me.sargunvohra.android.purduediningcourts.model.Location;

@Data
@ParcelablePlease
public class RetailLocation implements Location, Parcelable {

    String Name;
    Address Address;
    String PhoneNumber;
    double Latitude;
    double Longitude;
    RetailNormalHours NormalHours;
    String Logo;
    String Banner;
    String Type;
    String Description;
    String DescriptionUrl;

    @Nullable
    String MenuUrl;
    public static final Creator<RetailLocation> CREATOR = new Creator<RetailLocation>() {
        public RetailLocation createFromParcel(Parcel source) {
            RetailLocation target = new RetailLocation();
            RetailLocationParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public RetailLocation[] newArray(int size) {
            return new RetailLocation[size];
        }
    };

    @Override
    public String getFullName() {
        return getName();
    }

    @Override
    public String getTileImage() {
        return getBanner();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        RetailLocationParcelablePlease.writeToParcel(this, dest, flags);
    }
}
