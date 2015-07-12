package me.sargunvohra.android.purduediningcourts.base.lce;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import retrofit.RetrofitError;

public interface LceView<ListItem> extends MvpView {
    void showLoading();

    void showContent();

    void showError(RetrofitError.Kind kind);

    void setData(List<ListItem> data);
}