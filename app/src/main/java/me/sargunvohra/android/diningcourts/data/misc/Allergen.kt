package me.sargunvohra.android.diningcourts.data.misc

import com.squareup.moshi.Json

data class Allergen(
        @Json(name = "Name")
        val name: String,
        @Json(name = "Value")
        val value: Boolean
)