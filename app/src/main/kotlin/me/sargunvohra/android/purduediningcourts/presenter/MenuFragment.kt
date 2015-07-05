package me.sargunvohra.android.purduediningcourts.presenter

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import it.gmariotti.cardslib.library.internal.Card
import it.gmariotti.cardslib.library.internal.CardHeader

import kotlinx.android.synthetic.fragment_menu.view.*
import me.sargunvohra.android.purduediningcourts.view.MenuCard
import me.sargunvohra.android.purduediningcourts.R
import me.sargunvohra.android.purduediningcourts.model.DiningCourt
import me.sargunvohra.android.purduediningcourts.model.DiningCourtMenu
import me.sargunvohra.android.purduediningcourts.model.DiningCourtService
import java.text.SimpleDateFormat
import java.util.*

public class MenuFragment : Fragment() {

    public var topView: View? = null
        private set

    public var location: DiningCourt = DiningCourt.values()[0]
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = getArguments()
        if (args != null) {
            location = DiningCourt.values()[args.getInt(ARG_LOCATION)]
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        topView = inflater!!.inflate(R.layout.fragment_menu, container, false)
        topView!!.title.setText(location.toString() + " Dining Court")
        return topView
    }

    public fun loadData(date: Calendar, info: DiningCourtMenu) {
        val view = topView ?: getView()

        view.timestamp.setText(SimpleDateFormat("E MMM d, yyyy").format(date.getTime()));
        var closed = true

        listOf(
                Triple("Breakfast", info.breakfast, view.breakfast),
                Triple("Lunch", info.lunch, view.lunch),
                Triple("Dinner", info.dinner, view.dinner)
        ).forEach { triple ->
            val mealMenu = triple.second
            if (mealMenu.size() > 0) {
                closed = false
                val card = MenuCard(getActivity(), mealMenu)
                val header = CardHeader(getActivity())
                header.setTitle(triple.first)
                card.addCardHeader(header)
                triple.third.setCard(card)
                triple.third.setVisibility(View.VISIBLE)
            } else {
                triple.third.setVisibility(View.GONE)
            }
        }
        view.closed.setVisibility(if (closed) View.VISIBLE else View.GONE)
    }

    companion object {
        private val ARG_LOCATION = "location"

        public fun newInstance(location: DiningCourt): MenuFragment {
            val fragment = MenuFragment()
            val args = Bundle()
            args.putInt(ARG_LOCATION, location.ordinal())
            fragment.setArguments(args)
            return fragment
        }
    }
}