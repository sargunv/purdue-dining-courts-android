package me.sargunvohra.android.purduediningcourts.presenter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import me.sargunvohra.android.purduediningcourts.model.DiningLocation
import me.sargunvohra.android.purduediningcourts.presenter.MenuFragment

import java.util.ArrayList
import java.util.Collections

public class MenuPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getPageTitle(position: Int): CharSequence {
        return DiningLocation.values().get(position).toString()
    }

    override fun getCount(): Int {
        return DiningLocation.values().size();
    }

    override fun getItem(position: Int): Fragment {
        return MenuFragment.newInstance(DiningLocation.values().get(position))
    }
}
