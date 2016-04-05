package me.sargunvohra.android.diningcourts.data.service

import me.sargunvohra.android.diningcourts.data.item.MenuItem
import me.sargunvohra.android.diningcourts.data.menu.DiningMenu
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DiningCourtService {
    @GET("locations/{loc}/{date}/")
    fun getDiningMenu(@Path("loc") location: String, @Path("date") date: String): Call<DiningMenu>

    @GET("items/{id}/")
    fun getMenuItem(@Path("id") id: String): Call<MenuItem>
}