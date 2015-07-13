package me.sargunvohra.android.purduediningcourts.model.dining;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.Date;
import java.util.List;

import lombok.Data;

@ParcelablePlease
@Data
public class DiningPeriod implements Parcelable {

    String Id;
    String Name;
    Date EffectiveDate;
    List<DiningDay> Days;
    public static final Creator<DiningPeriod> CREATOR = new Creator<DiningPeriod>() {
        public DiningPeriod createFromParcel(Parcel source) {
            DiningPeriod target = new DiningPeriod();
            DiningPeriodParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public DiningPeriod[] newArray(int size) {
            return new DiningPeriod[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        DiningPeriodParcelablePlease.writeToParcel(this, dest, flags);
    }
}
