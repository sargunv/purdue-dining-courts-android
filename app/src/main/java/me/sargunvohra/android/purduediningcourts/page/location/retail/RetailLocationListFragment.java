package me.sargunvohra.android.purduediningcourts.page.location.retail;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.CastedArrayListLceViewState;

import java.util.ArrayList;
import java.util.List;

import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.base.BaseListAdapter;
import me.sargunvohra.android.purduediningcourts.base.MainLceFragment;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocation;
import me.sargunvohra.android.purduediningcourts.page.location.LocationListAdapter;

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
        Context ctx = getActivity();
        startActivity(new RetailInfoActivityIntentBuilder(location).build(ctx));
    }

    @Override
    public LceViewState<List<RetailLocation>, MvpLceView<List<RetailLocation>>> createViewState() {
        return new CastedArrayListLceViewState<>();
    }

    @Override
    public List<RetailLocation> getData() {
        return adapter.getDataSet();
    }
}

