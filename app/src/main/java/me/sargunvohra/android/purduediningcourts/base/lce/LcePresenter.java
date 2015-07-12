package me.sargunvohra.android.purduediningcourts.base.lce;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import me.sargunvohra.android.purduediningcourts.DaggerModule;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public abstract class LcePresenter<ListItem, Wrapper> extends MvpBasePresenter<LceView<ListItem>> implements Callback<Wrapper> {

    @Getter
    @Inject
    DiningService service;

    public LcePresenter() {
        DaggerModule.getObjectGraph().inject(this);
    }

    public void loadData() {
        Timber.i("Loading %s...", getLocationType());
        getView().showLoading();
    }

    public abstract String getLocationType();

    public void presentData(List<ListItem> list) {
        if (isViewAttached()) {
            getView().setData(list);
            getView().showContent();
        }
    }

    @Override
    public void success(Wrapper wrapper, Response response) {
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
