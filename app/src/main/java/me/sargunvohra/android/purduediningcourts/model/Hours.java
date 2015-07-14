package me.sargunvohra.android.purduediningcourts.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import lombok.Data;

@Data
@ParcelablePlease
public class Hours implements Parcelable {

    String StartTime;
    String EndTime;

    private static DateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.US);

    public static final Creator<Hours> CREATOR = new Creator<Hours>() {
        public Hours createFromParcel(Parcel source) {
            Hours target = new Hours();
            HoursParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Hours[] newArray(int size) {
            return new Hours[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        HoursParcelablePlease.writeToParcel(this, dest, flags);
    }

    public String toSimpleString() {
        format.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        try {
            Calendar start = Calendar.getInstance();
            start.setTime(format.parse(getStartTime()));
            Calendar end = Calendar.getInstance();
            end.setTime(format.parse(getEndTime()));
            return String.format("%1$tR-%2$tR", start, end);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
