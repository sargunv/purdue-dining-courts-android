package me.sargunvohra.android.purduediningcourts.presenter

import android.net.Uri
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

import io.karim.MaterialTabs

import kotlinx.android.synthetic.activity_main.*
import kotlinx.android.synthetic.toolbar.*
import me.sargunvohra.android.purduediningcourts.presenter.MenuFragment
import me.sargunvohra.android.purduediningcourts.presenter.MenuPagerAdapter
import me.sargunvohra.android.purduediningcourts.R
import java.util.*


public class MainActivity : AppCompatActivity(), MenuFragment.MenuActivity {
    override val date: Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        pager.setAdapter(MenuPagerAdapter(getSupportFragmentManager()))
        tabs.setViewPager(pager)
        pager.setOffscreenPageLimit(5)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super<AppCompatActivity>.onCreateOptionsMenu(menu)
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }
}
