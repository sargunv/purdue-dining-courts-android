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
import me.sargunvohra.android.diningcourts.base.BaseSupportFragment
import me.sargunvohra.android.diningcourts.data.menu.DiningMenu
import me.sargunvohra.android.diningcourts.extension.setEmptyAdapter
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import org.jetbrains.anko.onClick
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@FragmentWithArgs
class DiningMenuFragment : BaseSupportFragment(), DiningMenuContract.View {

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
        val r = DiningMenuRenderer()
        val b = RendererBuilder(r)
        val c = ListAdapteeCollection<DiningMenu.Item>()
        c.addAll(
                content.meals.flatMap {
                    it.stations.flatMap {
                        it.items
                    }
                }
        )
        val a = RVRendererAdapter(b, c)
        contentList.swapAdapter(a, false)
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