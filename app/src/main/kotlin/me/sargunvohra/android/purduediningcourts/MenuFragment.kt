package me.sargunvohra.android.purduediningcourts

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import it.gmariotti.cardslib.library.internal.Card
import it.gmariotti.cardslib.library.internal.CardHeader

import kotlinx.android.synthetic.fragment_menu.view.*
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import java.text.SimpleDateFormat
import java.util.*

public class MenuFragment : Fragment() {

    private var location = DiningLocation.values()[0]
    private var date = Date()

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
        refreshData(view)
        return view
    }

    private fun refreshData(parent: View) {
        ServiceHandler.getLocationInfo(location, date) { data ->
            parent.timestamp.setText(SimpleDateFormat("MMM d yyyy").format(date));
            hashMapOf(
                    "Breakfast" to parent.breakfast,
                    "Lunch" to parent.lunch,
                    "Dinner" to parent.dinner
            ).forEach { pair ->
                val card = MenuCard(getActivity(), data[pair.key] as JSONArray)
                val header = CardHeader(getActivity())
                header.setTitle(pair.key)
                card.addCardHeader(header)
                pair.value.setCard(card)
                pair.value.setVisibility(View.VISIBLE)
            }
        }
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