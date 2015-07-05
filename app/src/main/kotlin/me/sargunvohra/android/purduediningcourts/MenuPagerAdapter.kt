package me.sargunvohra.android.purduediningcourts

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import me.sargunvohra.android.purduediningcourts.model.DiningCourt
import me.sargunvohra.android.purduediningcourts.MenuFragment

import java.util.ArrayList
import java.util.Collections

public class MenuPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getPageTitle(position: Int): CharSequence {
        return DiningCourt.values().get(position).toString()
    }

    override fun getCount(): Int {
        return DiningCourt.values().size();
    }

    override fun getItem(position: Int): Fragment {
        return MenuFragment.newInstance(DiningCourt.values().get(position))
    }
}
