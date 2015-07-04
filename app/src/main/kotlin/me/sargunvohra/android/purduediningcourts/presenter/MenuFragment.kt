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
import me.sargunvohra.android.purduediningcourts.model.DiningLocation
import me.sargunvohra.android.purduediningcourts.model.LocationInfo
import me.sargunvohra.android.purduediningcourts.model.ServiceHandler
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import java.text.SimpleDateFormat
import java.util.*

public class MenuFragment : Fragment() {

    public var location: DiningLocation = DiningLocation.values()[0]
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = getArguments()
        if (args != null) {
            location = DiningLocation.values()[args.getInt(ARG_LOCATION)]
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_menu, container, false)
        view.title.setText(location.toString() + " Dining Court")
        return view
    }

    public fun loadData(info: LocationInfo) {
        val view = getView()

        view.timestamp.setText(SimpleDateFormat("E MMM d, yyyy").format(info.date.getTime()));
        var closed = true

        hashMapOf(
                "Breakfast" to view.breakfast,
                "Lunch" to view.lunch,
                "Dinner" to view.dinner
        ).forEach { pair ->
            val mealMenu: LocationInfo.MealMenu? = info.menu[pair.key]
            if (mealMenu != null && mealMenu.stations.size() > 0) {
                closed = false
                val card = MenuCard(getActivity(), mealMenu)
                val header = CardHeader(getActivity())
                header.setTitle(pair.key)
                card.addCardHeader(header)
                pair.value.setCard(card)
                pair.value.setVisibility(View.VISIBLE)
            } else {
                pair.value.setVisibility(View.GONE)
            }
        }
        view.closed.setVisibility(if (closed) View.VISIBLE else View.GONE)
    }

    companion object {
        private val ARG_LOCATION = "location"

        public fun newInstance(location: DiningLocation): MenuFragment {
            val fragment = MenuFragment()
            val args = Bundle()
            args.putInt(ARG_LOCATION, location.ordinal())
            fragment.setArguments(args)
            return fragment
        }
    }
}