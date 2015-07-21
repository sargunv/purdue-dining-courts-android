package me.sargunvohra.android.purduediningcourts.page.today;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.Calendar;

import javax.inject.Inject;

import lombok.Data;
import me.sargunvohra.android.purduediningcourts.DaggerModule;
import me.sargunvohra.android.purduediningcourts.model.dining.DayMenu;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class TodayPresenter extends MvpBasePresenter<MvpLceView<TodayInfo>> {

    @Inject
    DiningService service;

    public TodayPresenter() {
        DaggerModule.getObjectGraph().inject(this);
    }

    public void loadData(String favoriteDiningCourt, final boolean pullToRefresh) {
        if (isViewAttached())
            getView().showLoading(false);

        final TodayInfo result = new TodayInfo();
        result.setFavoriteDiningCourt(favoriteDiningCourt);

        String date = String.format("%1$tm-%1$td-%1$tY", Calendar.getInstance());

        service.getDiningMenu(favoriteDiningCourt, date, new CallbackBase<DayMenu>(pullToRefresh) {
            @Override
            public void success(DayMenu dayMenu, Response response) {
                result.setCurrentMeal(dayMenu.getNextMeal());
                result.setCounter(result.getCounter() + 1);
                trySubmitResult(result);
            }
        });
    }

    private boolean trySubmitResult(TodayInfo result) {
        if (result.getCounter() >= 1 && isViewAttached()) {
            getView().setData(result);
            getView().showContent();
            return true;
        } else {
            return false;
        }
    }

    @Data
    private abstract class CallbackBase<T> implements Callback<T> {
        private final boolean pullToRefresh;

        @Override
        public void failure(RetrofitError error) {
            if (isViewAttached()) {
                getView().showError(error, pullToRefresh);
            }
        }
    }
}
