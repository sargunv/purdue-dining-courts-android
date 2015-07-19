package me.sargunvohra.android.purduediningcourts.page.location.dining;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.Calendar;

import javax.inject.Inject;

import me.sargunvohra.android.purduediningcourts.DaggerModule;
import me.sargunvohra.android.purduediningcourts.model.dining.item.Item;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class DiningItemPresenter extends MvpBasePresenter<MvpLceView<Item>> {

    @Inject
    DiningService service;

    public DiningItemPresenter() {
        DaggerModule.getObjectGraph().inject(this);
    }

    public void loadData(String itemId, final boolean pullToRefresh) {
        if (isViewAttached())
            getView().showLoading(pullToRefresh);

        String date = String.format("%1$tm-%1$td-%1$tY", Calendar.getInstance());
        Timber.d(date);

        service.getItem(itemId, new Callback<Item>() {
            @Override
            public void success(Item item, Response response) {
                if (isViewAttached()) {
                    getView().setData(item);
                    getView().showContent();
                }
            }

            private void failure(Throwable t) {
                if (isViewAttached()) {
                    getView().showError(t, pullToRefresh);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                failure((Throwable) error);
            }
        });
    }

}
