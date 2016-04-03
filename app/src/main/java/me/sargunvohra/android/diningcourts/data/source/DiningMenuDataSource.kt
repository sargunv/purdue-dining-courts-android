package me.sargunvohra.android.diningcourts.data.source

import com.karumi.rosie.repository.datasource.ReadableDataSource
import me.sargunvohra.android.diningcourts.data.service.DiningCourtServiceImpl

class DiningMenuDataSource : ReadableDataSource<DiningMenu.Key, DiningMenu> {
    override fun getByKey(key: DiningMenu.Key): DiningMenu {
        return DiningCourtServiceImpl.getDiningMenu(key.location, key.date).execute().body()
    }

    override fun getAll(): Collection<DiningMenu> {
        throw UnsupportedOperationException()
    }
}