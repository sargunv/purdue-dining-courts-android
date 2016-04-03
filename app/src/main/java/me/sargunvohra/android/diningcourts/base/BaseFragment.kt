package me.sargunvohra.android.diningcourts.base

import android.os.Bundle
import com.hannesdorfmann.fragmentargs.FragmentArgs
import com.karumi.rosie.view.RosieFragment
import org.jetbrains.anko.AnkoLogger

abstract class BaseFragment : RosieFragment(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentArgs.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}