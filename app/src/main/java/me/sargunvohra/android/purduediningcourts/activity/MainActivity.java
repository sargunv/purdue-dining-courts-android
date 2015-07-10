package me.sargunvohra.android.purduediningcourts.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.Bind;
import icepick.Icicle;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.fragment.AboutFragment;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    DiningService service;

    @Inject
    Handler drawerActionHandler;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.content_frame)
    FrameLayout contentFrame;

    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    @Icicle
    int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationView.setNavigationItemSelectedListener(this);
        MenuItem selected = navigationView.getMenu().findItem(selectedItem);
        if (selected != null)
            selected.setChecked(true);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // make sure it's a new selection
        if (menuItem.getItemId() == selectedItem)
            return false;
        selectedItem = menuItem.getItemId();

        // choose the proper fragment
        final Fragment newPage;
        switch (selectedItem) {
            case R.id.nav_item_about:
                newPage = AboutFragment.newInstance("About");
                break;
            default:
                // TODO select other pages
                newPage = null;
                Snackbar.make(contentFrame, "Operation not yet supported", Snackbar.LENGTH_SHORT).show();
                Timber.i("Selected: %s", menuItem);
        }

        // select the item and close the drawer
        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);

        // launch fragment after a delay
        if (newPage != null) {
            drawerActionHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.content_frame, newPage)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
            }, 250);
        }

        return true;
    }

    public void setToolbar(Toolbar bar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, bar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }
}
