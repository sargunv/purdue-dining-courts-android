package me.sargunvohra.android.diningcourts.main

import android.os.Bundle
import me.sargunvohra.android.diningcourts.R
import me.sargunvohra.android.diningcourts.base.BaseActivity
import me.sargunvohra.android.diningcourts.config.dagger.MainModule

class MainActivity : BaseActivity(){

    override fun getLayoutId() = R.layout.activity_main

    override fun getActivityScopeModules() = listOf(MainModule())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}