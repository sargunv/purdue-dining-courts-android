package me.sargunvohra.android.diningcourts.data.misc

import com.squareup.moshi.Json

data class NutritionFact(
        @Json(name = "DailyValue")
        val dailyValue: String?,
        @Json(name = "LabelValue")
        val labelValue: String?,
        @Json(name = "Name")
        val name: String,
        @Json(name = "Ordinal")
        val ordinal: Int
)