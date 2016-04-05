package me.sargunvohra.android.diningcourts.data.item

import com.karumi.rosie.repository.datasource.Identifiable
import com.squareup.moshi.Json
import me.sargunvohra.android.diningcourts.data.misc.Allergen
import me.sargunvohra.android.diningcourts.data.misc.NutritionFact

data class MenuItem(
        @Json(name = "Allergens")
        val allergens: List<Allergen>,
        @Json(name = "ID")
        val id: String,
        @Json(name = "Ingredients")
        val ingredients: String,
        @Json(name = "IsVegetarian")
        val isVegetarian: Boolean,
        @Json(name = "Name")
        val name: String,
        @Json(name = "Nutrition")
        val nutrition: List<NutritionFact>
): Identifiable<String> {
        override fun getKey() = id
}