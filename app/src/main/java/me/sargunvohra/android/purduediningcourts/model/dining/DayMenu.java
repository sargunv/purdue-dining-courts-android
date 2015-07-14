package me.sargunvohra.android.purduediningcourts.model.dining;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@ParcelablePlease
@Data
public class DayMenu implements Parcelable {

    String Location;
    String Date;
    String Notes;
    List<Meal> Meals;

    public List<Meal> getOpenMeals() {
        List<Meal> result = new ArrayList<>();
        for (Meal m : getMeals()) {
            if (m.getStations() != null && m.getStations().size() > 0) {
                result.add(m);
            }
        }
        return result;
    }

    public static final Creator<DayMenu> CREATOR = new Creator<DayMenu>() {
        public DayMenu createFromParcel(Parcel source) {
            DayMenu target = new DayMenu();
            DayMenuParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public DayMenu[] newArray(int size) {
            return new DayMenu[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        DayMenuParcelablePlease.writeToParcel(this, dest, flags);
    }
}
