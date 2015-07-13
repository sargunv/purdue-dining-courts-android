package me.sargunvohra.android.purduediningcourts.model.dining.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.List;

import lombok.Data;

@ParcelablePlease
@Data
public class ItemSchedule implements Parcelable {

    @SerializedName("ItemAppearance")
    List<ItemAppearance> ItemAppearances;
    public static final Creator<ItemSchedule> CREATOR = new Creator<ItemSchedule>() {
        public ItemSchedule createFromParcel(Parcel source) {
            ItemSchedule target = new ItemSchedule();
            ItemScheduleParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public ItemSchedule[] newArray(int size) {
            return new ItemSchedule[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ItemScheduleParcelablePlease.writeToParcel(this, dest, flags);
    }
}
