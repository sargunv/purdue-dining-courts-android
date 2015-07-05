package me.sargunvohra.android.purduediningcourts.activity

import android.app.DatePickerDialog
import android.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Debug
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import io.karim.MaterialTabs

import kotlinx.android.synthetic.activity_main.*
import kotlinx.android.synthetic.toolbar.*
import kotlinx.android.synthetic.fragment_menu.view.*

import me.sargunvohra.android.purduediningcourts.MenuFragment
import me.sargunvohra.android.purduediningcourts.MenuPagerAdapter
import me.sargunvohra.android.purduediningcourts.R
import me.sargunvohra.android.purduediningcourts.model.DiningCourt
import me.sargunvohra.android.purduediningcourts.model.DiningCourtMenu
import me.sargunvohra.android.purduediningcourts.network.DiningCourtService
import me.sargunvohra.android.purduediningcourts.presenter.MenuPresenter
import me.sargunvohra.android.purduediningcourts.activity.AboutActivity
import me.sargunvohra.android.purduediningcourts.DatePickerFragment
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response
import java.util.*

public class MenuActivity : AppCompatActivity(), MenuPresenter.TargetView {
    private val menuFragments: MutableList<MenuFragment> = ArrayList()
    private val presenter: MenuPresenter = MenuPresenter(this)

    override var loading: Boolean
    get() {
        return swipeRefresh.isRefreshing()
    } set(value) {
        swipeRefresh.setRefreshing(value)
    }

    override fun showMenu(date: Calendar, menus: Map<DiningCourt, DiningCourtMenu>) {
        menuFragments.forEach {
            it.showData(date, menus[it.location]!!)
        }
    }

    override fun showConnectionError() {
        Toast.makeText(this, getText(R.string.conn_error), Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapter = MenuPagerAdapter(getSupportFragmentManager())

        pager.setAdapter(adapter)
        pager.setOffscreenPageLimit(5)
        pager.addOnPageChangeListener(object: ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(pos: Int) {
                swipeRefresh.target = pager.getChildAt(pos)
            }
        })
        swipeRefresh.post { swipeRefresh.target = pager.getChildAt(0) }

        tabs.setViewPager(pager)

        swipeRefresh.setColorSchemeResources(R.color.accent)
        swipeRefresh.setOnRefreshListener { presenter.refreshMenu() }
        swipeRefresh.post { presenter.setDate(Calendar.getInstance()) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return super<AppCompatActivity>.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            R.id.action_previous -> presenter.addDate(-1)
            R.id.action_next -> presenter.addDate(+1)
            R.id.action_today -> presenter.setDate(Calendar.getInstance())
            R.id.action_pick_date -> DatePickerFragment { presenter.setDate(it) }.show(getSupportFragmentManager(), "datePicker")
            R.id.action_about -> startActivity(Intent(this, javaClass<AboutActivity>()))
            else -> return super<AppCompatActivity>.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onAttachFragment(fragment: Fragment?) {
        if (fragment != null && fragment is MenuFragment)
            menuFragments.add(fragment)
    }
}
