package me.sargunvohra.android.diningcourts.menu

import me.sargunvohra.android.diningcourts.data.menu.DiningMenu
import me.sargunvohra.android.diningcourts.data.menu.DiningMenuRepository
import nl.komponents.kovenant.task
import javax.inject.Inject

class DiningMenuUseCase @Inject constructor(
        private val repository: DiningMenuRepository
) {
    fun getDiningMenu(key: DiningMenu.Key) = task {
        repository.getByKey(key)
    }
}