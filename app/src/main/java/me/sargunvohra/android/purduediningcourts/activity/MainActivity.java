package me.sargunvohra.android.purduediningcourts.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import lombok.val;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocation;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocations;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocation;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocations;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class MainActivity extends DrawerActivity {

    @Inject
    DiningService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service.getDiningLocations(new Callback<DiningLocations>() {
            @Override
            public void success(DiningLocations diningLocations, Response response) {
                for (DiningLocation d : diningLocations.getLocations())
                    addLocationToMenu(d);
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.e(error.getMessage());
            }
        });

        service.getRetailLocations(new Callback<RetailLocations>() {
            @Override
            public void success(RetailLocations retailLocations, Response response) {
                for (RetailLocation d : retailLocations.getLocations())
                    addLocationToMenu(d);
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.e(error.getMessage());
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default: return super.onOptionsItemSelected(item);
        }
    }
}
