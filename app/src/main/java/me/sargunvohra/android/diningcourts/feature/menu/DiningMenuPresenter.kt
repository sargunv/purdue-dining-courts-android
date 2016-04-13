package me.sargunvohra.android.diningcourts.feature.menu

import com.karumi.rosie.domain.usecase.UseCaseHandler
import com.karumi.rosie.view.loading.RosiePresenterWithLoading
import me.sargunvohra.android.diningcourts.data.menu.DiningMenu
import nl.komponents.kovenant.ui.alwaysUi
import nl.komponents.kovenant.ui.failUi
import nl.komponents.kovenant.ui.successUi
import javax.inject.Inject

class DiningMenuPresenter @Inject constructor(
        useCaseHandler: UseCaseHandler,
        private val useCase: DiningMenuUseCase
) : RosiePresenterWithLoading<DiningMenuContract.View>(useCaseHandler), DiningMenuContract.Presenter {

    override fun requestContent(key: DiningMenu.Key) {
        view.hideContent()
        view.hideError()
        view.showLoading()

        useCase.getDiningMenu(key) alwaysUi {
            view.hideLoading()
        } successUi {
            view.showContent(it)
        } failUi {
            view.showError(it)
        }
    }
}