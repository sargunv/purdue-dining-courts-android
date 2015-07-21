package me.sargunvohra.android.purduediningcourts.page.today;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import lombok.Data;
import me.sargunvohra.android.purduediningcourts.model.dining.Meal;

@ParcelablePlease
@Data
public class TodayInfo implements Parcelable {

    int counter = 0;
    String favoriteDiningCourt;
    Meal currentMeal;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        TodayInfoParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<TodayInfo> CREATOR = new Creator<TodayInfo>() {
        public TodayInfo createFromParcel(Parcel source) {
            TodayInfo target = new TodayInfo();
            TodayInfoParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public TodayInfo[] newArray(int size) {
            return new TodayInfo[size];
        }
    };
}
