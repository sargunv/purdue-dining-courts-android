package me.sargunvohra.android.purduediningcourts

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
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import java.text.SimpleDateFormat
import java.util.*

public class MenuFragment : Fragment() {

    private var location = DiningLocation.values()[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = getArguments()
        if (args != null) {
            location = DiningLocation.values()[args.getInt(ARG_LOCATION)]
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_menu, container, false)
        view.swipeRefresh.setColorSchemeResources(R.color.accent)
        view.swipeRefresh.setOnRefreshListener({ refreshData(view) })
        view.swipeRefresh.post({ refreshData(view) })
        return view
    }

    private fun refreshData(parent: View) {
        parent.swipeRefresh.setRefreshing(true);

        val activity = getActivity()
        ServiceHandler.getLocationInfo(location, if (activity is MenuActivity) activity.date else Date()) { info ->

            parent.title.setText(location.toString() + " Dining Court")
            Log.v("LocationInfo", info.toString())

            if (info == null) {
                Toast.makeText(getActivity(), R.string.conn_error, Toast.LENGTH_SHORT).show()
            } else {
                parent.timestamp.setText(SimpleDateFormat("MMM d yyyy").format(info.date));

                hashMapOf(
                        "Breakfast" to parent.breakfast,
                        "Lunch" to parent.lunch,
                        "Dinner" to parent.dinner
                ).forEach { pair ->
                    val mealMenu: LocationInfo.MealMenu? = info.menu[pair.key]
                    if (mealMenu != null) {
                        val card = MenuCard(getActivity(), mealMenu)
                        val header = CardHeader(getActivity())
                        header.setTitle(pair.key)
                        card.addCardHeader(header)
                        pair.value.setCard(card)
                        pair.value.setVisibility(View.VISIBLE)
                    }
                }
            }

            parent.swipeRefresh.setRefreshing(false);
        }
    }

    public interface MenuActivity {
        val date: Date
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