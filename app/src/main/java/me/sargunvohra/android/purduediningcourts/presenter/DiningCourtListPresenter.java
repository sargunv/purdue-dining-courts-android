package me.sargunvohra.android.purduediningcourts.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.sql.Time;

import javax.inject.Inject;

import me.sargunvohra.android.purduediningcourts.DaggerModule;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocations;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import me.sargunvohra.android.purduediningcourts.view.DiningCourtListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class DiningCourtListPresenter extends MvpBasePresenter<DiningCourtListView> {

    @Inject
    DiningService service;

    public DiningCourtListPresenter() {
        DaggerModule.getObjectGraph().inject(this);
    }

    public void loadDiningCourts() {
        Timber.i("Loading dining courts...");
        getView().showLoading();

        service.getDiningLocations(new Callback<DiningLocations>() {
            @Override
            public void success(DiningLocations diningLocations, Response response) {
                if (isViewAttached()) {
                    getView().setData(diningLocations);
                    getView().showContent();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (isViewAttached()) {
                    getView().showError();
                }
            }
        });
    }

}
