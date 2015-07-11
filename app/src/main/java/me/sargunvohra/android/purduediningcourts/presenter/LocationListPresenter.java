package me.sargunvohra.android.purduediningcourts.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import lombok.Getter;
import me.sargunvohra.android.purduediningcourts.DaggerModule;
import me.sargunvohra.android.purduediningcourts.model.Location;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import me.sargunvohra.android.purduediningcourts.view.LocationListView;

public abstract class LocationListPresenter<T extends Location> extends MvpBasePresenter<LocationListView<T>> {

    @Getter
    @Inject
    DiningService service;

    public LocationListPresenter() {
        DaggerModule.getObjectGraph().inject(this);
    }

    public abstract void loadData();
}
