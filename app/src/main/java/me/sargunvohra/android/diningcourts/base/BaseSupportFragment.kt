package me.sargunvohra.android.diningcourts.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.hannesdorfmann.fragmentargs.FragmentArgs
import org.jetbrains.anko.AnkoLogger

abstract class BaseSupportFragment : Fragment(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentArgs.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}