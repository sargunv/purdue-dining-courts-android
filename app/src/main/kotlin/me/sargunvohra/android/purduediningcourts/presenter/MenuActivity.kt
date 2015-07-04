package me.sargunvohra.android.purduediningcourts.presenter

import android.app.FragmentManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

import io.karim.MaterialTabs

import kotlinx.android.synthetic.activity_main.*
import kotlinx.android.synthetic.toolbar.*
import kotlinx.android.synthetic.fragment_menu.view.*

import me.sargunvohra.android.purduediningcourts.presenter.MenuFragment
import me.sargunvohra.android.purduediningcourts.presenter.MenuPagerAdapter
import me.sargunvohra.android.purduediningcourts.R
import java.util.*


public class MenuActivity : AppCompatActivity() {
    public val date: Calendar = Calendar.getInstance()
    private val menuFragments: MutableList<MenuFragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        pager.setAdapter(MenuPagerAdapter(getSupportFragmentManager()))
        tabs.setViewPager(pager)
        pager.setOffscreenPageLimit(5)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return super<AppCompatActivity>.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.getItemId()) {
                R.id.action_previous -> {
                    date.add(Calendar.DATE, -1)
                    refreshMenus()
                    return true
                }
                R.id.action_next -> {
                    date.add(Calendar.DATE, 1)
                    refreshMenus()
                    return true
                }
                R.id.action_today-> {
                    date.setTime(Date())
                    refreshMenus()
                    return true
                }
            }
        }
        return super<AppCompatActivity>.onOptionsItemSelected(item)
    }

    override fun onAttachFragment(fragment: Fragment?) {
        if (fragment != null && fragment is MenuFragment)
            menuFragments.add(fragment)
    }

    public fun refreshMenus() {
        menuFragments.forEach { it.getView().swipeRefresh.post { it.refreshData(it.getView()) } }
    }
}
