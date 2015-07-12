package me.sargunvohra.android.purduediningcourts.page.location.dining;

import me.sargunvohra.android.purduediningcourts.base.lce.LcePresenter;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocation;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocations;
import retrofit.client.Response;

public class DiningLocationListPresenter extends LcePresenter<DiningLocation, DiningLocations> {

    @Override
    public void loadData() {
        super.loadData();
        getService().getDiningLocations(this);
    }

    @Override
    public void success(DiningLocations diningLocations, Response response) {
        super.success(diningLocations, response);
        presentData(diningLocations.getLocations());
    }

    @Override
    public String getLocationType() {
        return "dining courts";
    }

}
