package me.sargunvohra.android.diningcourts.menu

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.karumi.rosie.view.Presenter
import com.pedrogomez.renderers.ListAdapteeCollection
import com.pedrogomez.renderers.RVRendererAdapter
import com.pedrogomez.renderers.RendererBuilder
import kotlinx.android.synthetic.main.fragment_lce_list.*
import me.sargunvohra.android.diningcourts.R
import me.sargunvohra.android.diningcourts.base.BaseFragment
import me.sargunvohra.android.diningcourts.data.menu.DiningMenu
import me.sargunvohra.android.diningcourts.extension.setEmptyAdapter
import org.jetbrains.anko.error
import org.jetbrains.anko.onClick
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@FragmentWithArgs
class DiningMenuFragment : BaseFragment(), DiningMenuContract.View {

    @Arg
    lateinit var location: String

    @Inject @Presenter
    lateinit var presenter: DiningMenuPresenter

    override fun getLayoutId() = R.layout.fragment_lce_list

    private fun reloadContent() {
        val today = SimpleDateFormat("MM-dd-yyyy").format(Date())
        presenter.requestContent(DiningMenu.Key(location, today))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentList.layoutManager = LinearLayoutManager(view.context)
        contentList.setEmptyAdapter()
        retryButton.onClick {
            reloadContent()
        }
    }

    override fun onResume() {
        super.onResume()
        reloadContent()
    }

    override fun showLoading() {
        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView.visibility = View.GONE
    }

    override fun showContent(content: DiningMenu) {
        contentList.swapAdapter(RVRendererAdapter(RendererBuilder(DiningMenuRenderer()), ListAdapteeCollection(content.toListItems())), false)
        contentList.visibility = View.VISIBLE
    }

    override fun hideContent() {
        contentList.visibility = View.GONE
    }

    override fun showError(throwable: Throwable) {
        error { throwable.message }
        errorView.visibility = View.VISIBLE
    }

    override fun hideError() {
        errorView.visibility = View.GONE
    }

    private fun DiningMenu.toListItems(): List<DiningMenuListItem> {
        return listOf(
                DiningMenuListItem.LocationHeader(location),
                DiningMenuListItem.MenuDate(date)
        ) + meals.flatMap { meal ->
            listOf(
                    DiningMenuListItem.MealHeader(meal.name)
            ) + if (meal.hours == null) {
                listOf(DiningMenuListItem.Closed())
            } else {
                meal.stations.flatMap { station ->
                    listOf(
                            DiningMenuListItem.StationHeader(station.name)
                    ) + station.items.map { item ->
                        DiningMenuListItem.Item(item.name, item.isVegetarian)
                    }
                }
            }
        }
    }
}