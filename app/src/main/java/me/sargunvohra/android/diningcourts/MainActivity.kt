package me.sargunvohra.android.diningcourts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import me.sargunvohra.android.diningcourts.json.DiningMenu
import me.sargunvohra.android.diningcourts.service.DiningCourtServiceImpl
import retrofit2.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val call = DiningCourtServiceImpl.getLocation("Earhart", "04-02-2016")

        call.enqueue(object : Callback<DiningMenu> {
            override fun onResponse(call: Call<DiningMenu>, response: Response<DiningMenu>) {
                Log.i("Menu", response.body().toString())
            }

            override fun onFailure(call: Call<DiningMenu>, t: Throwable) {
                Log.e("Menu", t.message)
            }
        })
    }
}