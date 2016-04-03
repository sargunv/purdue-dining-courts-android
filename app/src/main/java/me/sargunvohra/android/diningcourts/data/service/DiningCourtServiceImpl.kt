package me.sargunvohra.android.diningcourts.data.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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