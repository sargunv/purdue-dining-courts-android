package me.sargunvohra.android.diningcourts.feature.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import me.sargunvohra.android.diningcourts.feature.menu.DiningMenuFragmentBuilder
import java.util.*

class MainPagerAdapter(fm: FragmentManager, private val date: Date) : FragmentPagerAdapter(fm) {

    private val diningCourts: List<String> = listOf("Earhart", "Ford", "Hillenbrand", "Wiley", "Windsor")

    override fun getCount(): Int = diningCourts.count()

    override fun getPageTitle(position: Int): String = diningCourts[position]

    override fun getItem(position: Int): Fragment {
        val location = diningCourts[position]
        return DiningMenuFragmentBuilder(date, location).build()
    }
}