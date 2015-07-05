package me.sargunvohra.android.purduediningcourts.presenter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.toolbar.*
import kotlinx.android.synthetic.activity_about.*
import me.sargunvohra.android.purduediningcourts.BuildConfig
import me.sargunvohra.android.purduediningcourts.R

public class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true)
        getSupportActionBar().setDisplayShowHomeEnabled(true)
        version.setText(BuildConfig.VERSION_NAME)
        about.setText(Html.fromHtml(getString(R.string.app_about)))
        about.setMovementMethod(LinkMovementMethod.getInstance())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
