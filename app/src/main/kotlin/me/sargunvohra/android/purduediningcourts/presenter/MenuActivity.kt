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
import android.widget.Toast

import io.karim.MaterialTabs

import kotlinx.android.synthetic.activity_main.*
import kotlinx.android.synthetic.toolbar.*
import kotlinx.android.synthetic.fragment_menu.view.*

import me.sargunvohra.android.purduediningcourts.presenter.MenuFragment
import me.sargunvohra.android.purduediningcourts.presenter.MenuPagerAdapter
import me.sargunvohra.android.purduediningcourts.R
import me.sargunvohra.android.purduediningcourts.model.ServiceHandler
import java.util.*


public class MenuActivity : AppCompatActivity() {
    private val date: Calendar = Calendar.getInstance()
    private val menuFragments: MutableList<MenuFragment> = ArrayList()
    private var refreshing = 0
        set(value) {
            if (value < 0)
                throw IllegalArgumentException("Expected positive value but got: " + value)

            if (value > 0 && $refreshing == 0)
                swipeRefresh.setRefreshing(true)

            else if (value == 0 && $refreshing > 0)
                swipeRefresh.setRefreshing(false)

            $refreshing = value;
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        pager.setAdapter(MenuPagerAdapter(getSupportFragmentManager()))
        pager.setOffscreenPageLimit(5)

        tabs.setViewPager(pager)

        swipeRefresh.setColorSchemeResources(R.color.accent)
        swipeRefresh.setOnRefreshListener {refreshMenus()}
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
        menuFragments.forEach { frag ->
            ++refreshing
            ServiceHandler.getLocationInfo(frag.location, date) { data ->
                if (data != null) {
                    frag.loadData(data)
                } else {
                    Toast.makeText(this, R.string.conn_error, Toast.LENGTH_SHORT)
                }
                --refreshing
            }
        }
    }
}
