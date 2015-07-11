package me.sargunvohra.android.purduediningcourts.activity;

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
import android.view.View;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.InjectView;
import icepick.Icicle;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.fragment.AboutFragment;
import me.sargunvohra.android.purduediningcourts.fragment.DiningCourtListFragment;
import me.sargunvohra.android.purduediningcourts.fragment.PlaceholderFragment;
import me.sargunvohra.android.purduediningcourts.fragment.RetailListFragment;
import me.sargunvohra.android.purduediningcourts.service.DiningService;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    Handler drawerActionHandler;

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @InjectView(R.id.content_frame)
    FrameLayout contentFrame;

    @InjectView(R.id.navigation_view)
    NavigationView navigationView;

    @Icicle
    int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            selectedItem = R.id.nav_item_today;
            MenuItem menuItem = navigationView.getMenu().findItem(selectedItem);
            launchPage(choosePage(menuItem), 1);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        MenuItem menuItem = navigationView.getMenu().findItem(selectedItem);
        if (menuItem != null)
            selectMenuItem(menuItem);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == selectedItem)
            return false;

        final Fragment newPage = choosePage(menuItem);
        selectMenuItem(menuItem);
        closeDrawer();
        launchPage(newPage, 250);
        return true;
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void selectMenuItem(MenuItem menuItem) {
        if (menuItem == null)
            return;
        menuItem.setChecked(true);
        selectedItem = menuItem.getItemId();
    }

    private void launchPage(final Fragment newPage, int delay) {
        drawerActionHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.content_frame, newPage)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        }, delay);
    }

    private Fragment choosePage(MenuItem menuItem) {
        final Fragment newPage;
        switch (menuItem.getItemId()) {
            case R.id.nav_item_about:
                newPage = AboutFragment.newInstance(this);
                break;
            case R.id.nav_item_dining_courts:
                newPage = DiningCourtListFragment.newInstance(this);
                break;
            case R.id.nav_item_cafes:
                newPage = RetailListFragment.newInstance(getString(R.string.nav_cafes), "Cafés");
                break;
            case R.id.nav_item_restaurants:
                newPage = RetailListFragment.newInstance(getString(R.string.nav_restaurants), "Restaurants");
                break;
            case R.id.nav_item_markets:
                newPage = RetailListFragment.newInstance(getString(R.string.nav_markets), "Markets");
                break;
            default: // TODO
                newPage = PlaceholderFragment.newInstance(menuItem.getTitle().toString());
                Snackbar.make(contentFrame, "Operation not yet supported", Snackbar.LENGTH_SHORT).show();
        }
        return newPage;
    }

    public void setToolbar(Toolbar bar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, bar, R.string.open, R.string.close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
            }
        };
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }
}
