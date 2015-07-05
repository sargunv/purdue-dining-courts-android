package me.sargunvohra.android.purduediningcourts.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public data class DiningCourtItem(name: String, isVegetarian: Boolean) {

    @Expose
    @SerializedName("Name")
    public var name: String = name
        private set

    @Expose
    @SerializedName("IsVegetarian")
    public var isVegetarian: Boolean = isVegetarian
        private set
}