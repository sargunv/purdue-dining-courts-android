package me.sargunvohra.android.purduediningcourts.model.dining;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.Data;
import me.sargunvohra.android.purduediningcourts.model.Address;
import me.sargunvohra.android.purduediningcourts.model.Hours;
import me.sargunvohra.android.purduediningcourts.model.Location;
import me.sargunvohra.android.purduediningcourts.service.DiningServiceHelper;

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

    @Override
    public String getTimings(boolean twentyFourHourFormat) {
        DiningDay day = getToday();

        if (day == null)
            return null;

        StringBuilder str = new StringBuilder();
        Meal[] meals = day.getMeals().toArray(new Meal[day.getMeals().size()]);
        Arrays.sort(meals);

        boolean first = true;
        for (Meal m : meals) {
            if (m != null && m.getHours() != null) {
                Hours h = m.getHours();
                if (!first)
                    str.append("<br/>");
                first = false;
                str.append(String.format("%s<br/><i>%s</i>", m.getName(), h.toSimpleString(twentyFourHourFormat)));
            }
        }
        String result = str.toString();
        return (result.length() > 0 ? result : null);
    }

    @Nullable
    @Override
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

        return DiningServiceHelper.getToday(selectedPeriod.getDays());
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
