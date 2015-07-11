package me.sargunvohra.android.purduediningcourts.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import me.sargunvohra.android.purduediningcourts.model.Location;
import retrofit.RetrofitError;

public interface LocationListView<T extends Location> extends MvpView {
    void showLoading();
    void showContent();
    void showError(RetrofitError.Kind kind);
    void setData(List<T> data);
}