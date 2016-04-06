package me.sargunvohra.android.diningcourts.main

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.karumi.rosie.renderer.RosieRenderer
import com.pedrogomez.renderers.ListAdapteeCollection
import com.pedrogomez.renderers.RVRendererAdapter
import com.pedrogomez.renderers.Renderer
import com.pedrogomez.renderers.RendererBuilder
import kotlinx.android.synthetic.main.activity_main.*
import me.sargunvohra.android.diningcourts.R
import me.sargunvohra.android.diningcourts.base.BaseActivity
import me.sargunvohra.android.diningcourts.config.dagger.MainModule
import me.sargunvohra.lib.ktunits.days
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
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
    }
}