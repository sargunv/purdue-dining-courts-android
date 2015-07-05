package me.sargunvohra.android.purduediningcourts.network

import android.os.AsyncTask
import android.util.Log
import me.sargunvohra.android.purduediningcourts.model.DiningCourt
import me.sargunvohra.android.purduediningcourts.model.DiningCourtMenu
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.http.GET
import retrofit.http.Path
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

public object DiningCourtService {
    private interface DiningCourtServiceApi {
        @GET("/v1/locations/{location}/{date}")
        fun getMenu(@Path("location") location: String, @Path("date") date: String, response: Callback<DiningCourtMenu>)
    }

    private val restAdapter: RestAdapter = RestAdapter.Builder()
            .setEndpoint("http://api.hfs.purdue.edu/menus")
            .setLogLevel(RestAdapter.LogLevel.BASIC)
            .build()

    private val service = restAdapter.create(javaClass<DiningCourtServiceApi>())

    public fun getMenu(location: DiningCourt, date: Calendar, response: Callback<DiningCourtMenu>) {
        val locationArg = location.toString()
        val dateArg = SimpleDateFormat("MM-dd-yyyy").format(date.getTime())
        service.getMenu(locationArg, dateArg, response)
    }
}