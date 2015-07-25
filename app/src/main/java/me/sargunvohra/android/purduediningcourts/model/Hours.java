package me.sargunvohra.android.purduediningcourts.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

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

    public String toSimpleString(boolean twentyFourHourFormat) {
        String[] start = getStartTime().split(":");
        String[] end = getEndTime().split(":");

        if (!twentyFourHourFormat) {
            int h1 = Integer.parseInt(start[0]);
            int h2 = Integer.parseInt(end[0]);
            boolean startPm = h1 >= 12;
            boolean endPm = h2 >= 12;
            if (h1 == 0)
                h1 = 12;
            else if (h1 > 12)
                h1 %= 12;
            if (h2 == 0)
                h2 = 12;
            else if (h2 > 12)
                h2 %= 12;
            return String.format("%d:%s%s-%d:%s%s", h1, start[1], startPm ? "pm" : "am", h2, end[1], endPm ? "pm" : "am");
        } else {
            return String.format("%s:%s-%s:%s", start[0], start[1], end[0], end[1]);
        }
    }
}
