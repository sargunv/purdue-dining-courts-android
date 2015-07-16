package me.sargunvohra.android.purduediningcourts.page.location.dining;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.hannesdorfmann.fragmentargs.annotation.FragmentArgsInherited;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.CastedArrayListLceViewState;

import java.util.ArrayList;
import java.util.List;

import me.sargunvohra.android.purduediningcourts.LceAnimatorBugfix;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.base.BaseListAdapter;
import me.sargunvohra.android.purduediningcourts.base.MainLceFragment;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocation;
import me.sargunvohra.android.purduediningcourts.page.location.LocationListAdapter;

@FragmentArgsInherited
public class DiningLocationListFragment extends MainLceFragment<RecyclerView, List<DiningLocation>, MvpLceView<List<DiningLocation>>, DiningLocationListPresenter> implements BaseListAdapter.OnClickListener<DiningLocation> {

    LocationListAdapter<DiningLocation> adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new LocationListAdapter<>(new ArrayList<DiningLocation>(), this);
        contentView.setAdapter(adapter);
        contentView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        loadData(false);
    }

    @Override
    public DiningLocationListPresenter createPresenter() {
        return new DiningLocationListPresenter();
    }

    @Override
    protected void animateContentViewIn() {
        LceAnimatorBugfix.showContent(loadingView, contentView, errorView);
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
        Context ctx = getActivity();
        startActivity(new DiningMenuActivityIntentBuilder(location).build(ctx));
    }

    @Override
    public LceViewState<List<DiningLocation>, MvpLceView<List<DiningLocation>>> createViewState() {
        return new CastedArrayListLceViewState<>();
    }

    @Override
    public List<DiningLocation> getData() {
        return adapter.getDataSet();
    }
}

