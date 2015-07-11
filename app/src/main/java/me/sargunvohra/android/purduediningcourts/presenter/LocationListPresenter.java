package me.sargunvohra.android.purduediningcourts.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import me.sargunvohra.android.purduediningcourts.DaggerModule;
import me.sargunvohra.android.purduediningcourts.model.Location;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import me.sargunvohra.android.purduediningcourts.view.LocationListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public abstract class LocationListPresenter<T extends Location, D> extends MvpBasePresenter<LocationListView<T>> implements Callback<D> {

    @Getter
    @Inject
    DiningService service;

    public LocationListPresenter() {
        DaggerModule.getObjectGraph().inject(this);
    }

    public void loadData() {
        Timber.i("Loading %s...", getLocationType());
        getView().showLoading();
    }

    public abstract String getLocationType();

    public void presentData(List<T> list) {
        if (isViewAttached()) {
            getView().setData(list);
            getView().showContent();
        }
    }

    @Override
    public void success(D d, Response response) {
        Timber.i("Loaded %s", getLocationType());
    }

    @Override
    public void failure(RetrofitError error) {
        Timber.e(error, "Failed to load %s: %s", getLocationType(), error.getMessage());
        if (isViewAttached()) {
            getView().showError(error.getKind());
        }
    }
}
