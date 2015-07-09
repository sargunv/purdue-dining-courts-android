package me.sargunvohra.android.purduediningcourts.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.Bind;
import me.sargunvohra.android.purduediningcourts.DrawerItems;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.fragment.AboutFragment;
import me.sargunvohra.android.purduediningcourts.model.Location;
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

    @Bind(R.id.content_frame)
    FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO get date from a central source
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
    void onDrawerItemSelected(Location loc) {
        // TODO select location
        Snackbar.make(contentFrame, "Operation not yet supported", Snackbar.LENGTH_SHORT).show();
        Timber.i("Selected: %s \"%s\"", loc.getClass().getSimpleName(), loc.getName());
    }

    @Override
    void onDrawerItemSelected(DrawerItems item) {
        FragmentManager fm = getSupportFragmentManager();
        switch (item) {
            case INFO:
                Fragment fragment = AboutFragment.getInstance();
                fm.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();

                break;
            default:
                // TODO select page
                Snackbar.make(contentFrame, "Operation not yet supported", Snackbar.LENGTH_SHORT).show();
                Timber.i("Selected: %s", item);
        }
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
