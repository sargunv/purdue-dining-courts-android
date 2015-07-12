package me.sargunvohra.android.purduediningcourts.page.location.retail;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import me.sargunvohra.android.purduediningcourts.DaggerModule;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocation;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocations;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RetailLocationListPresenter extends MvpBasePresenter<MvpLceView<List<RetailLocation>>> {

    private final String locationType;

    @Inject
    DiningService service;

    public RetailLocationListPresenter(String locationType) {
        this.locationType = locationType;
        DaggerModule.getObjectGraph().inject(this);
    }

    public void loadData(final boolean pullToRefresh) {
        if (isViewAttached())
            getView().showLoading(false);

        service.getRetailLocations(new Callback<RetailLocations>() {
            @Override
            public void success(RetailLocations retailLocations, Response response) {
                if (isViewAttached()) {
                    List<RetailLocation> list = retailLocations.getLocations();
                    for (Iterator<RetailLocation> iterator = list.iterator(); iterator.hasNext(); )
                        if (!locationType.equals(iterator.next().getType()))
                            iterator.remove();
                    getView().setData(list);
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
