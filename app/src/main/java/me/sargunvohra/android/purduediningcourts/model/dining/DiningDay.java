package me.sargunvohra.android.purduediningcourts.model.dining;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.List;

import lombok.Data;
import me.sargunvohra.android.purduediningcourts.model.Hours;

@ParcelablePlease
@Data
public class DiningDay implements Parcelable {

    String Name;
    Integer DayOfWeek;
    List<Meal> Meals;
    Hours Hours;
    public static final Creator<DiningDay> CREATOR = new Creator<DiningDay>() {
        public DiningDay createFromParcel(Parcel source) {
            DiningDay target = new DiningDay();
            DiningDayParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public DiningDay[] newArray(int size) {
            return new DiningDay[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        DiningDayParcelablePlease.writeToParcel(this, dest, flags);
    }
}
