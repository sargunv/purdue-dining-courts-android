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

public class DiningLocationListPresenter extends LocationListPresenter<DiningLocation, DiningLocations> {

    @Override
    public void loadData() {
        super.loadData();
        getService().getDiningLocations(this);
    }

    @Override
    public void success(DiningLocations diningLocations, Response response) {
        super.success(diningLocations, response);
        presentData(diningLocations.getLocations());
    }

    @Override
    public String getLocationType() {
        return "dining courts";
    }

}
