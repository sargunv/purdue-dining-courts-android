package me.sargunvohra.android.diningcourts.base

import com.karumi.rosie.view.loading.RosiePresenterWithLoading

interface LceView<T> : RosiePresenterWithLoading.View {
    fun showContent(content: T)
    fun hideContent()
    fun showError(throwable: Throwable)
    fun hideError()
}