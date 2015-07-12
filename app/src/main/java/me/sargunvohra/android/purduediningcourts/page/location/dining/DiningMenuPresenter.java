package me.sargunvohra.android.purduediningcourts.page.location.dining;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import me.sargunvohra.android.purduediningcourts.DaggerModule;
import me.sargunvohra.android.purduediningcourts.model.dining.DayMenu;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocation;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocations;
import me.sargunvohra.android.purduediningcourts.model.dining.Meal;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DiningMenuPresenter extends MvpBasePresenter<MvpLceView<DayMenu>> {

    @Getter
    private final String location;

    @Inject
    DiningService service;

    public DiningMenuPresenter(String location) {
        this.location = location;
        DaggerModule.getObjectGraph().inject(this);
    }

    public void loadData(Calendar date, final boolean pullToRefresh) {
        if (isViewAttached())
            getView().showLoading(false);

        String dateStr = String.format("%1$tm-%1$td-%1$tY", date);

        service.getDiningMenu(getLocation(), dateStr, new Callback<DayMenu>() {
            @Override
            public void success(DayMenu dayMenu, Response response) {
                if (isViewAttached()) {
                    getView().setData(dayMenu);
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
