package me.sargunvohra.android.diningcourts.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.karumi.rosie.view.Presenter
import kotlinx.android.synthetic.main.fragment_blank.view.*
import me.sargunvohra.android.diningcourts.R
import me.sargunvohra.android.diningcourts.base.BaseSupportFragment
import me.sargunvohra.android.diningcourts.data.menu.DiningMenu
import org.jetbrains.anko.error
import javax.inject.Inject

@FragmentWithArgs
class DiningMenuFragment : BaseSupportFragment(), DiningMenuContract.View {

    @Arg
    lateinit var location: String

    @Inject @Presenter
    lateinit var presenter: DiningMenuPresenter

    override fun getLayoutId() = R.layout.fragment_blank

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        view?.placeholderText?.text = "Menu for '$location'"
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.requestContent(DiningMenu.Key(location, "04-02-2016"))
    }

    override fun showLoading() {
        view?.placeholderText?.text = "Loading..."
    }

    override fun hideLoading() {
        view?.placeholderText?.text = ""
    }

    override fun showContent(content: DiningMenu) {
        view?.placeholderText?.text = content.toString()
    }

    override fun hideContent() {
        view?.placeholderText?.text = ""
    }

    override fun showError(throwable: Throwable) {
        error { throwable.message }
        view?.placeholderText?.text = throwable.message
    }

    override fun hideError() {
        view?.placeholderText?.text = ""
    }
}