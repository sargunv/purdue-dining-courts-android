package me.sargunvohra.android.purduediningcourts.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocations;

public interface DiningCourtListView extends MvpView {
    void showLoading();
    void showContent();
    void showError();
    void setData(DiningLocations data);
}