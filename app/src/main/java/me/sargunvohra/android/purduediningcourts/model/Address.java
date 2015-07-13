package me.sargunvohra.android.purduediningcourts.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import lombok.Data;

@Data
@ParcelablePlease
public class Address implements Parcelable {

    String Street;
    String City;
    String State;
    String ZipCode;

    @Nullable
    String Country;

    @Nullable
    String CountryCode;
    public static final Creator<Address> CREATOR = new Creator<Address>() {
        public Address createFromParcel(Parcel source) {
            Address target = new Address();
            AddressParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        AddressParcelablePlease.writeToParcel(this, dest, flags);
    }
}
