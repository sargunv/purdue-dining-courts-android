package me.sargunvohra.android.purduediningcourts.presenter;

import java.util.Iterator;
import java.util.List;

import lombok.RequiredArgsConstructor;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocation;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocations;
import retrofit.client.Response;

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class RetailLocationListPresenter extends LcePresenter<RetailLocation, RetailLocations> {

    private final String locationType;

    @Override
    public void loadData() {
        super.loadData();
        getService().getRetailLocations(this);
    }

    @Override
    public String getLocationType() {
        return locationType;
    }

    @Override
    public void success(RetailLocations retailLocations, Response response) {
        super.success(retailLocations, response);
        List<RetailLocation> list = retailLocations.getLocations();
        for ( Iterator<RetailLocation> iterator = list.iterator(); iterator.hasNext(); )
            if (!locationType.equals(iterator.next().getType()))
                iterator.remove();
        presentData(list);
    }

}
