package me.sargunvohra.android.diningcourts.feature.menu

import me.sargunvohra.android.diningcourts.data.menu.DiningMenu
import java.util.*

sealed class DiningMenuListItem {

    class LocationHeader(val name: String) : DiningMenuListItem()

    class MenuDate(val date: Date) : DiningMenuListItem()

    class MealHeader(val name: String) : DiningMenuListItem()

    class Closed : DiningMenuListItem()

    class StationHeader(val name: String) : DiningMenuListItem()

    class Item(val name: String, val isVeg: Boolean) : DiningMenuListItem()

    class Divider : DiningMenuListItem()

    companion object {

        fun fromMenu(diningMenu: DiningMenu): List<DiningMenuListItem> {
            return listOf(
                    DiningMenuListItem.LocationHeader(diningMenu.location),
                    DiningMenuListItem.MenuDate(diningMenu.date)
            ) + diningMenu.meals.flatMap { fromMeal(it) }
        }

        private fun fromMeal(meal: DiningMenu.Meal): List<DiningMenuListItem> {
            return listOf(
                    DiningMenuListItem.Divider(),
                    DiningMenuListItem.MealHeader(meal.name)
            ) + if (meal.hours == null) {
                listOf(DiningMenuListItem.Closed())
            } else {
                meal.stations.flatMap { fromStation(it) }
            }
        }

        private fun fromStation(station: DiningMenu.Station): List<DiningMenuListItem> {
            return listOf(DiningMenuListItem.StationHeader(station.name)) + station.items.map { fromItem(it) }
        }

        private fun fromItem(item: DiningMenu.Item): DiningMenuListItem.Item {
            return DiningMenuListItem.Item(item.name, item.isVegetarian)
        }
    }
}