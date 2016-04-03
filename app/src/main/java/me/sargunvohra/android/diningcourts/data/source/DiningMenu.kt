package me.sargunvohra.android.diningcourts.data.source

import com.karumi.rosie.repository.datasource.Identifiable
import com.squareup.moshi.Json
import me.sargunvohra.android.diningcourts.data.obj.Allergen
import me.sargunvohra.android.diningcourts.data.obj.TimeRange

data class DiningMenu(
        @Json(name = "Location")
        val location: String,
        @Json(name = "Date")
        val date: String,
        @Json(name = "Meals")
        val meals: List<Meal>,
        @Json(name = "Notes")
        val notes: String?
): Identifiable<DiningMenu.Key> {

    data class Item(
            @Json(name = "ID")
            val id: String,
            @Json(name = "Name")
            val name: String,
            @Json(name = "IsVegetarian")
            val isVegetarian: Boolean,
            @Json(name = "Allergens")
            val allergens: List<Allergen>?
    )

    data class Station(
            @Json(name = "Name")
            val name: String,
            @Json(name = "Items")
            val items: List<Item>
    )

    data class Meal(
            @Json(name = "Order")
            val order: Int,
            @Json(name = "Name")
            val name: String,
            @Json(name = "Hours")
            val hours: TimeRange?,
            @Json(name = "Status")
            val status: String,
            @Json(name = "Stations")
            val stations: List<Station>
    )

    data class Key(
            val location: String,
            val date: String
    )

    override fun getKey() = Key(location, date)
}