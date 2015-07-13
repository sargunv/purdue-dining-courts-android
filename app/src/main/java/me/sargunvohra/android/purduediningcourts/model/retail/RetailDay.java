package me.sargunvohra.android.purduediningcourts.model.retail;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.List;

import lombok.Data;
import me.sargunvohra.android.purduediningcourts.model.Hours;

@Data
@ParcelablePlease
public class RetailDay implements Parcelable {

    String Name;
    Integer DayOfWeek;
    List<Hours> Hours;
    public static final Creator<RetailDay> CREATOR = new Creator<RetailDay>() {
        public RetailDay createFromParcel(Parcel source) {
            RetailDay target = new RetailDay();
            RetailDayParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public RetailDay[] newArray(int size) {
            return new RetailDay[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        RetailDayParcelablePlease.writeToParcel(this, dest, flags);
    }
}