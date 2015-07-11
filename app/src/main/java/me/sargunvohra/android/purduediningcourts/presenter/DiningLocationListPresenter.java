package me.sargunvohra.android.purduediningcourts.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import me.sargunvohra.android.purduediningcourts.DaggerModule;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocation;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocations;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import me.sargunvohra.android.purduediningcourts.view.LocationListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class DiningLocationListPresenter extends LocationListPresenter<DiningLocation> {

    @Override
    public void loadData() {
        Timber.i("Loading dining courts...");
        getView().showLoading();

        getService().getDiningLocations(new Callback<DiningLocations>() {
            @Override
            public void success(DiningLocations diningLocations, Response response) {
                if (isViewAttached()) {
                    Timber.i("Loaded dining courts");
                    getView().setData(diningLocations.getLocations());
                    getView().showContent();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.e(error, "Failed to load dining courts because: %s", error.getMessage());
                if (isViewAttached()) {
                    getView().showError(error.getKind());
                }
            }
        });
    }

}
