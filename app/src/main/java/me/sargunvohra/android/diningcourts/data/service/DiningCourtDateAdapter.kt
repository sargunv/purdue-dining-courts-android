package me.sargunvohra.android.diningcourts.data.service

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

class DiningCourtDateAdapter {

    private val formatter = SimpleDateFormat("MM/dd/yyyy")

    @ToJson
    @Suppress("unused")
    fun toJson(date: Date) = formatter.format(date)

    @FromJson
    @Suppress("unused")
    fun fromJson(dateStr: String) = formatter.parse(dateStr)
}