package me.sargunvohra.android.diningcourts.data.item

import com.karumi.rosie.repository.RosieRepository
import com.karumi.rosie.repository.datasource.CacheDataSource
import com.karumi.rosie.repository.datasource.ReadableDataSource
import javax.inject.Inject

class MenuItemRepository @Inject constructor(
        val apiDataSource: ReadableDataSource<String, MenuItem>,
        val cacheDataSource: CacheDataSource<String, MenuItem>
) : RosieRepository<String, MenuItem>() {
    init {
        addReadableDataSources(apiDataSource)
        addCacheDataSources(cacheDataSource)
    }
}