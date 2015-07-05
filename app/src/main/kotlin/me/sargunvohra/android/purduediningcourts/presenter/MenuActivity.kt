package me.sargunvohra.android.purduediningcourts.presenter

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

import me.sargunvohra.android.purduediningcourts.presenter.MenuFragment
import me.sargunvohra.android.purduediningcourts.presenter.MenuPagerAdapter
import me.sargunvohra.android.purduediningcourts.R
import me.sargunvohra.android.purduediningcourts.model.DiningCourtMenu
import me.sargunvohra.android.purduediningcourts.model.DiningCourtService
import me.sargunvohra.android.purduediningcourts.view.DatePickerFragment
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response
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
        swipeRefresh.setOnRefreshListener { refreshMenus() }
        swipeRefresh.post { refreshMenus() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return super<AppCompatActivity>.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            R.id.action_previous -> {
                date.add(Calendar.DATE, -1)
                refreshMenus()
            }
            R.id.action_next -> {
                date.add(Calendar.DATE, 1)
                refreshMenus()
            }
            R.id.action_today -> {
                date.setTime(Date())
                refreshMenus()
            }
            R.id.action_pick_date -> {
                val picker = DatePickerFragment {
                    date.setTime(it.getTime())
                    refreshMenus()
                }
                picker.show(getSupportFragmentManager(), "datePicker")
            }
            R.id.action_about -> {
                startActivity(Intent(this, javaClass<AboutActivity>()))
            }
            else -> {
                return super<AppCompatActivity>.onOptionsItemSelected(item)
            }
        }
        return true
    }

    override fun onAttachFragment(fragment: Fragment?) {
        if (fragment != null && fragment is MenuFragment)
            menuFragments.add(fragment)
    }

    public fun refreshMenus() {
        val context = this
        val requestDate = date

        menuFragments.forEach { frag ->
            ++refreshing
            DiningCourtService.getMenu(frag.location, requestDate, object: Callback<DiningCourtMenu> {
                override fun success(data: DiningCourtMenu?, response: Response?) {
                    if (data != null)
                        frag.loadData(requestDate, data)
                    --refreshing
                }

                override fun failure(error: RetrofitError?) {
                    Log.e("Service", error?.getMessage())
                    Toast.makeText(context, R.string.conn_error, Toast.LENGTH_SHORT).show()
                    --refreshing
                }
            })
        }
    }
}
