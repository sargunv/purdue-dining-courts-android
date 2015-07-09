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
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import timber.log.Timber;

public class MainActivity extends DrawerActivity {

    @Inject
    DiningService service;

    @Bind(R.id.content_frame)
    FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    void onDrawerItemSelected(DrawerItems item) {
        FragmentManager fm = getSupportFragmentManager();
        switch (item) {
            case ABOUT:
                Fragment fragment = AboutFragment.getInstance();
                fm.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();
                break;
            default:
                // TODO select other pages
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
