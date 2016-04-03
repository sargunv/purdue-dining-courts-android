package me.sargunvohra.android.diningcourts.base

import android.os.Bundle
import com.hannesdorfmann.fragmentargs.FragmentArgs
import com.karumi.rosie.view.RosieSupportFragment
import org.jetbrains.anko.AnkoLogger

abstract class BaseFragment : RosieSupportFragment(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentArgs.inject(this)
    }
}