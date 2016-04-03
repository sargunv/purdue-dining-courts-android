package me.sargunvohra.android.diningcourts.main

import android.os.Bundle
import com.karumi.rosie.domain.usecase.UseCaseHandler
import com.karumi.rosie.view.Presenter
import com.karumi.rosie.view.loading.RosiePresenterWithLoading
import kotlinx.android.synthetic.main.activity_main.*
import me.sargunvohra.android.diningcourts.R
import me.sargunvohra.android.diningcourts.base.BaseActivity
import me.sargunvohra.android.diningcourts.base.LceView
import me.sargunvohra.android.diningcourts.config.dagger.MainModule
import me.sargunvohra.android.diningcourts.data.source.DiningMenu
import me.sargunvohra.android.diningcourts.data.source.DiningMenuRepository
import nl.komponents.kovenant.task
import nl.komponents.kovenant.ui.alwaysUi
import nl.komponents.kovenant.ui.failUi
import nl.komponents.kovenant.ui.successUi
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject @Presenter
    lateinit var presenter: MainPresenter

    override fun getLayoutId() = R.layout.activity_main

    override fun getActivityScopeModules() = listOf(MainModule())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testText.text = "test"
    }

    override fun showContent(content: DiningMenu) {
        testText.text = content.toString()
    }

    override fun hideContent() {
        testText.text = ""
    }

    override fun showError(throwable: Throwable) {
        testText.text = "Error: ${throwable.message}"
    }

    override fun hideError() {
        testText.text = ""
    }

    override fun hideLoading() {
        testText.text = ""
    }

    override fun showLoading() {
        testText.text = "Loading..."
    }
}

class MainPresenter @Inject constructor(
        useCaseHandler: UseCaseHandler,
        val mainUseCase: MainUseCase
) : RosiePresenterWithLoading<MainContract.View>(useCaseHandler), MainContract.Presenter {
    override fun update() {
        super.update()

        view.hideError()
        view.hideContent()
        view.showLoading()

        mainUseCase[DiningMenu.Key("Hillenbrand", "04-02-2016")] alwaysUi {
            view.hideLoading()
        } successUi {
            view.showContent(it)
        } failUi {
            view.showError(it)
        }
    }
}

interface MainContract {
    interface Presenter
    interface View: LceView<DiningMenu>
}

class MainUseCase @Inject constructor(val repo: DiningMenuRepository) {
    operator fun get(key: DiningMenu.Key) = task { repo.getByKey(key) }
}