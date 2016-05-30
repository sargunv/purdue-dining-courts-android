package me.sargunvohra.android.diningcourts.feature.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsBuilder
import khronos.week
import khronos.weeks
import kotlinx.android.synthetic.main.activity_main.*
import me.sargunvohra.android.diningcourts.R
import me.sargunvohra.android.diningcourts.feature.about.AboutLibsListener
import me.sargunvohra.android.diningcourts.base.BaseActivity
import me.sargunvohra.android.diningcourts.dagger.MainModule
import org.jetbrains.anko.info
import org.jetbrains.anko.onClick
import java.util.*

class MainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_main

    override fun getActivityScopeModules() = listOf(MainModule())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setup action bar
        setSupportActionBar(toolbar)

        // setup view pager
        viewPager.adapter = MainPagerAdapter(supportFragmentManager, Date())
        tabLayout.setupWithViewPager(viewPager)

        // setup fab toolbar
        fabToolbar.setFab(fab)
        fab.onClick {
            fabToolbar.expandFab()
        }

        // setup fab toolbar with view pager
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) = fabToolbar.slideInFab()
        })

        // setup date picker
        datePicker.applyDateRange(1.week.ago..2.weeks.since)
        datePicker.scrollToPosition(7)
        datePicker.selectItem(7)
        datePicker.addItemClickListener {
            datePicker.selectItem(it.index)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                LibsBuilder().apply {
                    activityStyle = Libs.ActivityStyle.LIGHT_DARK_TOOLBAR
                    activityTheme = R.style.AppTheme
                    autoDetect = false
                    withListener(AboutLibsListener())
                    withLibraries(
                            "dagger",
                            "retrofit"
                    )
                }.start(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}