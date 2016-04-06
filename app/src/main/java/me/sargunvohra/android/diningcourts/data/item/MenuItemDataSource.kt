package me.sargunvohra.android.diningcourts.data.item

import com.karumi.rosie.repository.datasource.ReadableDataSource
import me.sargunvohra.android.diningcourts.data.service.DiningCourtServiceImpl

class MenuItemDataSource : ReadableDataSource<String, MenuItem> {
    override fun getByKey(key: String): MenuItem {
        return DiningCourtServiceImpl.getMenuItem(key).execute().body()
    }

    override fun getAll(): Collection<MenuItem> {
        throw UnsupportedOperationException()
    }
}