package me.sargunvohra.android.diningcourts.menu

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.karumi.rosie.view.Presenter
import com.pedrogomez.renderers.ListAdapteeCollection
import com.pedrogomez.renderers.RVRendererAdapter
import kotlinx.android.synthetic.main.fragment_lce_list.*
import me.sargunvohra.android.diningcourts.R
import me.sargunvohra.android.diningcourts.base.BaseFragment
import me.sargunvohra.android.diningcourts.data.menu.DiningMenu
import me.sargunvohra.android.diningcourts.extension.setEmptyAdapter
import org.jetbrains.anko.error
import org.jetbrains.anko.onClick
import java.util.*
import javax.inject.Inject

@FragmentWithArgs
class DiningMenuFragment : BaseFragment(), DiningMenuContract.View {

    @Arg
    lateinit var location: String

    @Arg
    lateinit var date: Date

    @Inject @Presenter
    lateinit var presenter: DiningMenuPresenter

    override fun getLayoutId() = R.layout.fragment_lce_list

    private fun reloadContent() {
        presenter.requestContent(DiningMenu.Key(location, date))
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
        val rendererBuilder = DiningMenuRendererBuilder()
        val collection = ListAdapteeCollection(DiningMenuListItem.fromMenu(content))
        val adapter = RVRendererAdapter(rendererBuilder, collection)
        contentList.swapAdapter(adapter, false)
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
}