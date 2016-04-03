package me.sargunvohra.android.diningcourts.data.service

import me.sargunvohra.android.diningcourts.data.source.DiningMenu
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DiningCourtService {
    @GET("locations/{loc}/{date}/")
    fun getDiningMenu(@Path("loc") location: String, @Path("date") date: String): Call<DiningMenu>
}