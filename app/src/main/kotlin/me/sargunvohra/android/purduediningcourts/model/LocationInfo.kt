package me.sargunvohra.android.purduediningcourts.model

import me.sargunvohra.android.purduediningcourts.model.DiningLocation
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import java.util.*

public data class LocationInfo(val location: DiningLocation, val date: Date, val menu: Map<String, LocationInfo.MealMenu>) {

    constructor(location: DiningLocation, date: Date, json: JSONObject) : this(location, date, linkedMapOf(
            "Breakfast" to MealMenu(json["Breakfast"] as JSONArray),
            "Lunch" to MealMenu(json["Lunch"] as JSONArray),
            "Dinner" to MealMenu(json["Dinner"] as JSONArray)
    ))

    public data class MealMenu(val stations: List<StationMenu>) {
        constructor(json: JSONArray) : this(json.map { StationMenu(it as JSONObject) })
    }

    public data class StationMenu(val name: String, val items: List<MenuItem>) {
        constructor(json: JSONObject) : this(json["Name"] as String, (json["Items"] as JSONArray).map { MenuItem(it as JSONObject) })
    }

    public data class MenuItem(val name: String, val isVegetarian: Boolean) {
        constructor(json: JSONObject) : this(json["Name"] as String, json["IsVegetarian"] as Boolean)
    }
}