package me.sargunvohra.android.purduediningcourts.fragment;


import android.content.Context;
import android.os.Bundle;

import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocation;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocations;
import me.sargunvohra.android.purduediningcourts.presenter.DiningLocationListPresenter;
import timber.log.Timber;

public class DiningCourtListFragment extends LocationListFragment<DiningLocation, DiningLocations> {

    public static DiningCourtListFragment newInstance(Context ctx) {
        DiningCourtListFragment fragment = new DiningCourtListFragment();
        Bundle args = new Bundle();
        args.putString(BaseFragment.ARG_TITLE, ctx.getString(R.string.nav_dining_courts));
        fragment.setArguments(args);
        return fragment;
    }

    public DiningCourtListFragment() {}

    @Override
    public DiningLocationListPresenter createPresenter() {
        return new DiningLocationListPresenter();
    }

    @Override
    public void onClick(DiningLocation location) {
        Timber.d("Clicked: %s", location);
    }
}

