package me.sargunvohra.android.purduediningcourts.presenter

import me.sargunvohra.android.purduediningcourts.model.DiningCourt
import me.sargunvohra.android.purduediningcourts.model.DiningCourtMenu
import me.sargunvohra.android.purduediningcourts.network.DiningCourtService
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response
import java.util.*

public class MenuPresenter(val activity: MenuPresenter.TargetView) {

    public interface TargetView {
        var loading: Boolean
        fun showMenu(date: Calendar, menus: Map<DiningCourt, DiningCourtMenu>)
        fun showConnectionError()
    }

    private val cal = Calendar.getInstance()

    public var date: Date
        get() {
            return cal.getTime()
        }
        set(value) {
            cal.setTime(value)
            refreshMenu()
        }

    public fun addDays(offset: Int) {
        cal.add(Calendar.DAY_OF_MONTH, offset)
        refreshMenu()
    }

    public fun refreshMenu() {
        val menus = HashMap<DiningCourt, DiningCourtMenu>()
        activity.loading = true
        DiningCourt.values().forEach {
            DiningCourtService.getMenu(it, cal, LoadMenuCallback(menus, it))
        }
    }

    inner class LoadMenuCallback(val menus:HashMap<DiningCourt, DiningCourtMenu>, val location: DiningCourt) : Callback<DiningCourtMenu> {

        override fun success(menu: DiningCourtMenu, response: Response) {
            menus[location] = menu
            if (menus.size() == DiningCourt.values().size()) {
                activity.showMenu(cal, menus)
                activity.loading = false
            }
        }

        override fun failure(error: RetrofitError) {
            if (activity.loading) {
                activity.showConnectionError()
                activity.loading = false
            }
        }

    }
}