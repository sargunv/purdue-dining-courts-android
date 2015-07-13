package me.sargunvohra.android.purduediningcourts.model.retail;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import lombok.Data;

@Data
@ParcelablePlease
public class RetailPeriod implements Parcelable {
    public static final Creator<RetailPeriod> CREATOR = new Creator<RetailPeriod>() {
        public RetailPeriod createFromParcel(Parcel source) {
            RetailPeriod target = new RetailPeriod();
            RetailPeriodParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public RetailPeriod[] newArray(int size) {
            return new RetailPeriod[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        RetailPeriodParcelablePlease.writeToParcel(this, dest, flags);
    }
}
