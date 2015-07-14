package me.sargunvohra.android.purduediningcourts.page.location.dining;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import javax.inject.Inject;

import me.sargunvohra.android.purduediningcourts.DaggerModule;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocation;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocations;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DiningLocationListPresenter extends MvpBasePresenter<MvpLceView<List<DiningLocation>>> {

    @Inject
    DiningService service;

    public DiningLocationListPresenter() {
        DaggerModule.getObjectGraph().inject(this);
    }

    public void loadData(final boolean pullToRefresh) {
        if (isViewAttached())
            getView().showLoading(pullToRefresh);

        service.getDiningLocations(new Callback<DiningLocations>() {
            @Override
            public void success(DiningLocations diningLocations, Response response) {
                if (isViewAttached()) {
                    getView().setData(diningLocations.getLocations());
                    getView().showContent();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (isViewAttached()) {
                    getView().showError(error, pullToRefresh);
                }
            }
        });
    }
}
