package me.sargunvohra.android.purduediningcourts.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public data class DiningCourtMenu(breakfast: List<DiningCourtStation>, lunch: List<DiningCourtStation>, dinner: List<DiningCourtStation>) {

    @Expose
    @SerializedName("Breakfast")
    public var breakfast: List<DiningCourtStation> = breakfast
        private set

    @Expose
    @SerializedName("Lunch")
    public var lunch: List<DiningCourtStation> = lunch
        private set

    @Expose
    @SerializedName("Dinner")
    public var dinner: List<DiningCourtStation> = dinner
        private set
}