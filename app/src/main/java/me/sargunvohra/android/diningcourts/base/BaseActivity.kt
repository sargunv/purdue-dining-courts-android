package me.sargunvohra.android.diningcourts.base

import com.karumi.rosie.view.RosieAppCompatActivity
import org.jetbrains.anko.AnkoLogger

abstract class BaseActivity : RosieAppCompatActivity(), AnkoLogger {
    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount != 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}