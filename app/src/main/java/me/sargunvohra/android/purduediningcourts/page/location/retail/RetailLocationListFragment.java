package me.sargunvohra.android.purduediningcourts.page.location.retail;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.ArrayList;
import java.util.List;

import me.sargunvohra.android.purduediningcourts.LceAnimatorBugfix;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.base.BaseListAdapter;
import me.sargunvohra.android.purduediningcourts.base.MainLceFragment;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocation;
import me.sargunvohra.android.purduediningcourts.page.location.LocationListAdapter;
import timber.log.Timber;

public class RetailLocationListFragment extends MainLceFragment<RecyclerView, List<RetailLocation>, MvpLceView<List<RetailLocation>>, RetailLocationListPresenter> implements BaseListAdapter.OnClickListener<RetailLocation> {

    @Arg
    String locationType = "";

    LocationListAdapter<RetailLocation> adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new LocationListAdapter<>(new ArrayList<RetailLocation>(), this);
        contentView.setAdapter(adapter);
        contentView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        loadData(false);
    }

    @Override
    protected void animateContentViewIn() {
        LceAnimatorBugfix.showContent(loadingView, contentView, errorView);
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

