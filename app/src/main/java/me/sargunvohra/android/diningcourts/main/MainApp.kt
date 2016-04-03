package me.sargunvohra.android.diningcourts.main

import com.karumi.rosie.application.RosieApplication
import me.sargunvohra.android.diningcourts.config.dagger.AppModule
import nl.komponents.kovenant.android.androidUiDispatcher
import nl.komponents.kovenant.ui.KovenantUi

class MainApp : RosieApplication() {
    override fun getApplicationModules() = listOf(AppModule())

    override fun onCreate() {
        super.onCreate()
        KovenantUi.uiContext {
            dispatcher = androidUiDispatcher()
        }
    }
}