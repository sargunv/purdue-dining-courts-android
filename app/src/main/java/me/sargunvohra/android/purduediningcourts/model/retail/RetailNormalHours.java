package me.sargunvohra.android.purduediningcourts.model.retail;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.List;

import lombok.Data;

@Data
@ParcelablePlease
public class RetailNormalHours implements Parcelable {

    List<RetailDay> Days;

    @Nullable
    RetailPeriod Period;
    public static final Creator<RetailNormalHours> CREATOR = new Creator<RetailNormalHours>() {
        public RetailNormalHours createFromParcel(Parcel source) {
            RetailNormalHours target = new RetailNormalHours();
            RetailNormalHoursParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public RetailNormalHours[] newArray(int size) {
            return new RetailNormalHours[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        RetailNormalHoursParcelablePlease.writeToParcel(this, dest, flags);
    }
}
