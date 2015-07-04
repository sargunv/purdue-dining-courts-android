package me.sargunvohra.android.purduediningcourts

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


public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        pager.setAdapter(MenuPagerAdapter(getSupportFragmentManager()))
        tabs.setViewPager(pager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }
}
