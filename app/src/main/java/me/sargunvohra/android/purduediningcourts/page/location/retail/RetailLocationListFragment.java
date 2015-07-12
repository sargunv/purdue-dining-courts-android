package me.sargunvohra.android.purduediningcourts.page.location.retail;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.ArrayList;
import java.util.List;

import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.base.BaseLceFragment;
import me.sargunvohra.android.purduediningcourts.base.BaseListAdapter;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocation;
import me.sargunvohra.android.purduediningcourts.page.location.LocationListAdapter;
import timber.log.Timber;

public class RetailLocationListFragment extends BaseLceFragment<RecyclerView, List<RetailLocation>, MvpLceView<List<RetailLocation>>, RetailLocationListPresenter> implements BaseListAdapter.OnClickListener<RetailLocation> {

    protected static final String ARG_LOC_TYPE = "retail_location_type";

    String locationType = "";

    public RetailLocationListFragment() {
    }

    public static RetailLocationListFragment newInstance(String title, String locationType) {
        RetailLocationListFragment fragment = new RetailLocationListFragment();
        Bundle args = new Bundle();
        args.putString(BaseLceFragment.ARG_TITLE, title);
        args.putString(ARG_LOC_TYPE, locationType);
        fragment.setArguments(args);
        return fragment;
    }

    LocationListAdapter<RetailLocation> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            locationType = getArguments().getString(ARG_LOC_TYPE);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new LocationListAdapter<>(new ArrayList<RetailLocation>(), this);
        contentView.setAdapter(adapter);
        contentView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        loadData(false);
    }

    @Override
    public RetailLocationListPresenter createPresenter() {
        return new RetailLocationListPresenter(locationType);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_location_list;
    }

    @Override
    public void setData(List<RetailLocation> data) {
        adapter.setDataSet(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }

    @Override
    public void onClick(RetailLocation location) {
        Timber.d("Clicked %s", location);
    }
}

