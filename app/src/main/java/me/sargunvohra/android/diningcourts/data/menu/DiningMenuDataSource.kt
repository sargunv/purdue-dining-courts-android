package me.sargunvohra.android.diningcourts.data.menu

import com.karumi.rosie.repository.datasource.ReadableDataSource
import me.sargunvohra.android.diningcourts.data.service.DiningCourtServiceImpl
import java.text.SimpleDateFormat

class DiningMenuDataSource : ReadableDataSource<DiningMenu.Key, DiningMenu> {
    override fun getByKey(key: DiningMenu.Key): DiningMenu {
        val date = SimpleDateFormat("MM-dd-yyyy").format(key.date)
        return DiningCourtServiceImpl.getDiningMenu(key.location, date).execute().body()
    }

    override fun getAll(): Collection<DiningMenu> {
        throw UnsupportedOperationException()
    }
}