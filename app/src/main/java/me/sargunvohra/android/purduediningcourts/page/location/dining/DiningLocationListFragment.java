package me.sargunvohra.android.purduediningcourts.page.location.dining;


import android.content.Context;
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
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocation;
import me.sargunvohra.android.purduediningcourts.page.location.LocationListAdapter;
import timber.log.Timber;

public class DiningLocationListFragment extends BaseLceFragment<RecyclerView, List<DiningLocation>, MvpLceView<List<DiningLocation>>, DiningLocationListPresenter> implements BaseListAdapter.OnClickListener<DiningLocation> {

    public DiningLocationListFragment() {
    }

    public static DiningLocationListFragment newInstance(Context ctx) {
        DiningLocationListFragment fragment = new DiningLocationListFragment();
        Bundle args = new Bundle();
        args.putString(BaseLceFragment.ARG_TITLE, ctx.getString(R.string.nav_dining_courts));
        fragment.setArguments(args);
        return fragment;
    }

    LocationListAdapter<DiningLocation> adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new LocationListAdapter<>(new ArrayList<DiningLocation>(), this);
        contentView.setAdapter(adapter);
        contentView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        loadData(false);
    }

    @Override
    public DiningLocationListPresenter createPresenter() {
        return new DiningLocationListPresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_location_list;
    }

    @Override
    public void setData(List<DiningLocation> data) {
        adapter.setDataSet(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }

    @Override
    public void onClick(DiningLocation location) {
        Timber.d("Clicked %s", location);
    }
}

