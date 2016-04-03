package me.sargunvohra.android.diningcourts.menu

sealed class DiningMenuListItem {

    class LocationHeader(val name: String): DiningMenuListItem()

    class Divider: DiningMenuListItem()

    class MenuDate(val date: String): DiningMenuListItem()

    class MealHeader(val name: String): DiningMenuListItem()

    class Closed: DiningMenuListItem()

    class StationHeader(val name: String): DiningMenuListItem()

    class Item(val name: String, val isVeg: Boolean): DiningMenuListItem()
}