package me.sargunvohra.android.diningcourts.service

import me.sargunvohra.android.diningcourts.json.DiningMenu
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DiningCourtService {
    @GET("locations/{name}/{date}/")
    fun getLocation(@Path("name") name: String, @Path("date") date: String): Call<DiningMenu>
}

object DiningCourtServiceImpl : DiningCourtService by (Retrofit.Builder().apply {
    baseUrl("https://api.hfs.purdue.edu/menus/v2/")
    addConverterFactory(MoshiConverterFactory.create())
    client(OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().apply {
                addHeader("Accept", "application/json")
            }.build())
        }
    }.build())
}.build().create(DiningCourtService::class.java))