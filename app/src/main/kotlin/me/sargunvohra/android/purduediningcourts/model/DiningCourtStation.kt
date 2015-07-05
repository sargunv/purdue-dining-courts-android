package me.sargunvohra.android.purduediningcourts.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public data class DiningCourtStation(name: String, items: List<DiningCourtItem>) {

    @Expose
    @SerializedName("Name")
    public var name: String = name
        private set

    @Expose
    @SerializedName("Items")
    public var items: List<DiningCourtItem> = items
        private set
}