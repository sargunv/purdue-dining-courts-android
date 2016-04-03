package me.sargunvohra.android.diningcourts.base

//import android.content.Context
import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
import com.hannesdorfmann.fragmentargs.FragmentArgs
//import com.karumi.rosie.view.PresenterLifeCycleLinker
import com.karumi.rosie.view.RosieSupportFragment
import org.jetbrains.anko.AnkoLogger

abstract class BaseSupportFragment : RosieSupportFragment(), AnkoLogger {

//    private val presenterLifeCycleLinker = PresenterLifeCycleLinker()
//    private var injected = false
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        injectDependencies()
//    }
//
//    override fun onAttach(activity: Context) {
//        super.onAttach(activity)
//        injectDependencies()
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState).let {
//            injectDependencies(); it
//        }
//    }
//
//    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        presenterLifeCycleLinker.initializeLifeCycle(this, this)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentArgs.inject(this)
    }

//    override fun onResume() {
//        super.onResume()
//        presenterLifeCycleLinker.updatePresenters(this)
//    }
//
//    override fun onPause() {
//        super.onPause()
//        presenterLifeCycleLinker.pausePresenters()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        presenterLifeCycleLinker.destroyPresenters()
//    }
//
//    open fun shouldInjectFragment(): Boolean = false
//
//    private fun injectDependencies() {
//        val activity = activity
//        if (!injected && activity is BaseActivity && shouldInjectFragment()) {
//            activity.inject(this)
//            injected = true
//        }
//    }
}