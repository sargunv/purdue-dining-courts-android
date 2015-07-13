package me.sargunvohra.android.purduediningcourts.model.dining;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.Data;
import me.sargunvohra.android.purduediningcourts.model.Address;
import me.sargunvohra.android.purduediningcourts.model.Location;

@ParcelablePlease
@Data
public class DiningLocation implements Location, Parcelable {

    String Name;
    Address Address;
    String PhoneNumber;
    double Latitude;
    double Longitude;
    List<DiningPeriod> NormalHours;
    String[] Images;
    String FormalName;
    public static final Creator<DiningLocation> CREATOR = new Creator<DiningLocation>() {
        public DiningLocation createFromParcel(Parcel source) {
            DiningLocation target = new DiningLocation();
            DiningLocationParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public DiningLocation[] newArray(int size) {
            return new DiningLocation[size];
        }
    };

    @Override
    public String getFullName() {
        return getFormalName();
    }

    @Override
    public String getTileImage() {
        if (getImages().length == 0)
            return null;
        else
            return getImages()[0];
    }

    @Nullable
    public DiningDay getToday() {
        if (NormalHours.size() <= 0)
            return null;

        Date today = new Date();
        Date selectedDate = null;
        DiningPeriod selectedPeriod = null;
        for (DiningPeriod period : getNormalHours()) {
            Date d = period.getEffectiveDate();
            if (selectedDate == null || (d.compareTo(today) <= 0 && d.compareTo(selectedDate) > 0)) {
                selectedDate = d;
                selectedPeriod = period;
            }
        }

        if (selectedPeriod == null || selectedPeriod.getDays() == null)
            return null;

        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        for (DiningDay day : selectedPeriod.getDays()) {
            if (dayOfWeek == day.getDayOfWeek())
                return day;
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        DiningLocationParcelablePlease.writeToParcel(this, dest, flags);
    }
}
