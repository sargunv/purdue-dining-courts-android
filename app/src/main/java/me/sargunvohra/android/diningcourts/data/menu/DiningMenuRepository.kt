package me.sargunvohra.android.diningcourts.data.menu

import com.karumi.rosie.repository.RosieRepository
import com.karumi.rosie.repository.datasource.CacheDataSource
import com.karumi.rosie.repository.datasource.ReadableDataSource
import javax.inject.Inject

class DiningMenuRepository @Inject constructor(
        val apiDataSource: ReadableDataSource<DiningMenu.Key, DiningMenu>,
        val cacheDataSource: CacheDataSource<DiningMenu.Key, DiningMenu>
) : RosieRepository<DiningMenu.Key, DiningMenu>() {
    init {
        addReadableDataSources(apiDataSource)
        addCacheDataSources(cacheDataSource)
    }
}