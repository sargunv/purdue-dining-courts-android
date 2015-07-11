package me.sargunvohra.android.purduediningcourts.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.sargunvohra.android.purduediningcourts.DaggerModule;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocation;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocations;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocation;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocations;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import me.sargunvohra.android.purduediningcourts.view.LocationListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class RetailLocationListPresenter extends LocationListPresenter<RetailLocation> {

    @Getter
    private final String locationType;

    @Override
    public void loadData() {
        Timber.i("Loading %s ...", locationType);
        getView().showLoading();

        getService().getRetailLocations(new Callback<RetailLocations>() {
            @Override
            public void success(RetailLocations retailLocations, Response response) {
                Timber.i("Loaded %s", locationType);

                if (isViewAttached()) {
                    List<RetailLocation> list = retailLocations.getLocations();
                    for ( Iterator<RetailLocation> iterator = list.iterator(); iterator.hasNext(); )
                        if (!locationType.equals(iterator.next().getType()))
                            iterator.remove();
                    getView().setData(list);
                    getView().showContent();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.i("Failed to load %s", locationType);
                if (isViewAttached()) {
                    getView().showError();
                }
            }
        });
    }

}
