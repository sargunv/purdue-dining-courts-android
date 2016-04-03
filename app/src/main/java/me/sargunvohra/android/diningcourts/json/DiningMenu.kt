package me.sargunvohra.android.diningcourts.json

import com.squareup.moshi.Json

data class DiningMenuItemAllergen(
        @Json(name = "Name")
        val name: String,
        @Json(name = "Value")
        val value: Boolean
)

data class DiningMenuItem(
        @Json(name = "ID")
        val id: String,
        @Json(name = "Name")
        val name: String,
        @Json(name = "IsVegetarian")
        val isVegetarian: Boolean,
        @Json(name = "Allergens")
        val allergens: List<DiningMenuItemAllergen>?
)

data class DiningMenuStation(
        @Json(name = "Name")
        val name: String,
        @Json(name = "Items")
        val items: List<DiningMenuItem>
)

data class DiningMenuMealHours(
        @Json(name = "StartTime")
        val startTime: String,
        @Json(name = "EndTime")
        val endTime: String
)

data class DiningMenuMeal(
        @Json(name = "Order")
        val order: Int,
        @Json(name = "Name")
        val name: String,
        @Json(name = "Hours")
        val hours: DiningMenuMealHours?,
        @Json(name = "Status")
        val status: String,
        @Json(name = "Stations")
        val stations: List<DiningMenuStation>
)

data class DiningMenu(
        @Json(name = "Location")
        val location: String,
        @Json(name = "Date")
        val date: String,
        @Json(name = "Meals")
        val meals: List<DiningMenuMeal>,
        @Json(name = "Notes")
        val notes: String
)