package me.sargunvohra.android.purduediningcourts.fragment;


import android.os.Bundle;

import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocation;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocations;
import me.sargunvohra.android.purduediningcourts.presenter.RetailLocationListPresenter;
import timber.log.Timber;

public class RetailListFragment extends LocationListFragment<RetailLocation, RetailLocations> {

    protected static final String ARG_LOC_TYPE = "retail_location_type";

    String locationType = "";

    public static RetailListFragment newInstance(String title, String locationType) {
        RetailListFragment fragment = new RetailListFragment();
        Bundle args = new Bundle();
        args.putString(BaseFragment.ARG_TITLE, title);
        args.putString(ARG_LOC_TYPE, locationType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            locationType = getArguments().getString(ARG_LOC_TYPE);
        }
    }

    public RetailListFragment() {}

    @Override
    public RetailLocationListPresenter createPresenter() {
        return new RetailLocationListPresenter(locationType);
    }

    @Override
    public void onClick(RetailLocation location) {
        Timber.d("Clicked: %s", location);
    }
}

