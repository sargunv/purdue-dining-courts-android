package me.sargunvohra.android.purduediningcourts.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import me.sargunvohra.android.purduediningcourts.model.Location;

public interface LocationListView<T extends Location> extends MvpView {
    void showLoading();
    void showContent();
    void showError();
    void setData(List<T> data);
}