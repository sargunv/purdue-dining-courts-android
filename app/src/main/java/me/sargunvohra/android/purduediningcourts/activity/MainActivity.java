package me.sargunvohra.android.purduediningcourts.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

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


public class MainActivity extends BaseActivity {

    @Inject
    protected DiningService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service.getRetailLocations(new Callback<RetailLocations>() {
            @Override
            public void success(RetailLocations result, Response response) {
                Timber.d("success: %s, %s", result, response);
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.d("failure: %s", error);
            }
        });

        service.getDiningLocations(new Callback<DiningLocations>() {
            @Override
            public void success(DiningLocations result, Response response) {
                Timber.d("success: %s, %s", result, response);
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.d("failure: %s", error);
            }
        });
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
