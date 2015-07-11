package me.sargunvohra.android.purduediningcourts.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.InjectView;
import lombok.Getter;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocations;
import me.sargunvohra.android.purduediningcourts.presenter.DiningCourtListPresenter;
import me.sargunvohra.android.purduediningcourts.view.DiningCourtListView;
import timber.log.Timber;

public class DiningCourtListFragment extends BaseFragment<DiningCourtListView, DiningCourtListPresenter> implements DiningCourtListView {

    @InjectView(R.id.dataView)
    TextView dataView;

    @Getter
    @InjectView(R.id.contentView)
    View contentView;

    @Getter
    @InjectView(R.id.loadingView)
    View loadingView;

    @Getter
    @InjectView(R.id.errorView)
    View errorView;

    public static DiningCourtListFragment newInstance(Context ctx) {
        DiningCourtListFragment fragment = new DiningCourtListFragment();
        Bundle args = new Bundle();
        args.putString(BaseFragment.ARG_TITLE, ctx.getString(R.string.nav_dining_courts));
        fragment.setArguments(args);
        return fragment;
    }

    public DiningCourtListFragment() {}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    @Override
    public DiningCourtListPresenter createPresenter() {
        return new DiningCourtListPresenter();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_dining_court_list;
    }

    @Override
    public void showLoading() {
        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent() {
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setData(DiningLocations data) {
        dataView.setText(data.toString());
    }

    public void loadData() {
        presenter.loadDiningCourts();
    }
}

