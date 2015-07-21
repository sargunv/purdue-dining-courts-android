package me.sargunvohra.android.purduediningcourts.page.today;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.annotation.FragmentArgsInherited;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.ParcelableDataLceViewState;

import butterknife.InjectView;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.base.MainLceFragment;
import me.sargunvohra.android.purduediningcourts.model.dining.Meal;
import me.sargunvohra.android.purduediningcourts.model.dining.Station;
import me.sargunvohra.android.purduediningcourts.model.dining.item.Item;

@FragmentArgsInherited
public class TodayFragment extends MainLceFragment<NestedScrollView, TodayInfo, MvpLceView<TodayInfo>, TodayPresenter> {

    private TodayInfo data;
    private SharedPreferences prefs;

    @InjectView(R.id.favoriteDiningCourt)
    TextView favoriteDiningCourt;

    @InjectView(R.id.favoriteDiningCourtTitle)
    TextView favoriteDiningCourtTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData(false);
    }

    @Override
    public TodayPresenter createPresenter() {
        return new TodayPresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_today;
    }

    @Override
    public void setData(TodayInfo data) {
        this.data = data;
        if (prefFavDiningCourt().equals("None")) {
            favoriteDiningCourt.setText(R.string.noFavoriteDiningCourt);
        } else if (data.getCurrentMeal() == null) {
            favoriteDiningCourtTitle.setText(String.format("Next meal at %s", data.getFavoriteDiningCourt()));
            favoriteDiningCourt.setText(R.string.noMeal);
        } else {
            favoriteDiningCourtTitle.setText(String.format("%s at %s", data.getCurrentMeal().getName(), data.getFavoriteDiningCourt()));
            String html = mealToHtml(data.getCurrentMeal());
            favoriteDiningCourt.setText(Html.fromHtml(html));
        }
    }

    private String mealToHtml(Meal meal) {
        StringBuilder str = new StringBuilder();
        str.append("<p><em>").append(meal.getHours().toSimpleString()).append("</em></p>");
        if (meal.getStations() != null) {
            for (Station s : meal.getStations()) {
                str.append("<h5>").append(s.getName()).append("</h5>");
                for (Item i : s.getItems()) {
                    str.append(i.getName()).append("<br />");
                }
            }
        }
        return str.toString();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(prefFavDiningCourt(), pullToRefresh);
    }

    @Override
    public LceViewState<TodayInfo, MvpLceView<TodayInfo>> createViewState() {
        return new ParcelableDataLceViewState<>();
    }

    @Override
    public TodayInfo getData() {
        return data;
    }

    private String prefFavDiningCourt() {
        return prefs.getString("pref_favoriteDiningCourt", "None");
    }
}

