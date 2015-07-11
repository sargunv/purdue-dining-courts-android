package me.sargunvohra.android.purduediningcourts.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import lombok.Getter;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.adapter.LocationListAdapter;
import me.sargunvohra.android.purduediningcourts.model.Location;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocation;
import me.sargunvohra.android.purduediningcourts.presenter.LocationListPresenter;
import me.sargunvohra.android.purduediningcourts.view.LocationListView;
import retrofit.RetrofitError;
import timber.log.Timber;

public abstract class LocationListFragment<T extends Location> extends BaseFragment<LocationListView<T>, LocationListPresenter<T>> implements LocationListView<T>, LocationListAdapter.OnClickListener<T> {

    @Getter
    @InjectView(R.id.contentView)
    RecyclerView contentView;

    @Getter
    @InjectView(R.id.loadingView)
    View loadingView;

    @Getter
    @InjectView(R.id.errorView)
    View errorView;

    @Getter
    @InjectView(R.id.errorMessage)
    TextView errorMessage;

    LocationListAdapter<T> adapter;

    public LocationListFragment() {}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new LocationListAdapter<>(new ArrayList<T>(), this);
        contentView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        contentView.setAdapter(adapter);
        loadData();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_location_list;
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
    public void showError(RetrofitError.Kind kind) {
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);

        switch (kind) {
            case NETWORK:
                errorMessage.setText(R.string.network_error);
                break;
            case CONVERSION:
                errorMessage.setText(R.string.conversion_error);
                break;
            case HTTP:
                errorMessage.setText(R.string.http_error);
                break;
            default:
                Timber.wtf("Encountered unknown error type: %s", kind);
            case UNEXPECTED:
                errorMessage.setText(R.string.unexpected_error);
                break;
        }
    }

    @Override
    public void setData(List<T> data) {
        adapter.setDataSet(data);
    }

    @Override
    public void onClick(T location) {
        String name = location.getFullName();
        Snackbar.make(getView(), "Clicked " + name, Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.retryButton)
    public void loadData() {
        presenter.loadData();
    }
}

