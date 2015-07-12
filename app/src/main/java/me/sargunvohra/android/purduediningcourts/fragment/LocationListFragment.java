package me.sargunvohra.android.purduediningcourts.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.adapter.LocationListAdapter;
import me.sargunvohra.android.purduediningcourts.model.Location;

public abstract class LocationListFragment<ListItem extends Location, Wrapper> extends LceFragment<ListItem, Wrapper, LocationListAdapter<ListItem>> {

    public LocationListFragment() {}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public LocationListAdapter<ListItem> buildAdapter() {
        return new LocationListAdapter<>(new ArrayList<ListItem>(), this);
    }

    @Override
    public RecyclerView.LayoutManager buildLayoutManager() {
        return new GridLayoutManager(getActivity(), 2);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_location_list;
    }
}

