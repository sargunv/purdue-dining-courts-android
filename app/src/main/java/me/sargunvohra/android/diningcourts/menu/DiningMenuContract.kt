package me.sargunvohra.android.diningcourts.menu

import me.sargunvohra.android.diningcourts.base.LceView
import me.sargunvohra.android.diningcourts.data.menu.DiningMenu

interface DiningMenuContract {

    interface Presenter {
        fun requestContent(key: DiningMenu.Key)
    }

    interface View: LceView<DiningMenu>
}