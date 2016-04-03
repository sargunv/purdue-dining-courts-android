package me.sargunvohra.android.diningcourts.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import kotlinx.android.synthetic.main.fragment_blank.view.*
import me.sargunvohra.android.diningcourts.R
import me.sargunvohra.android.diningcourts.base.BaseSupportFragment

@FragmentWithArgs
class DiningMenuFragment : BaseSupportFragment() {
    @Arg
    lateinit var message: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        view.placeholderText.text = "Placeholder for '$message'"
        return view
    }
}