package me.sargunvohra.android.purduediningcourts.page.location.dining;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.ArrayList;

import butterknife.InjectView;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.model.dining.DayMenu;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocation;
import me.sargunvohra.android.purduediningcourts.model.dining.Meal;
import me.sargunvohra.android.purduediningcourts.service.DiningServiceHelper;
import se.emilsjolander.intentbuilder.Extra;
import se.emilsjolander.intentbuilder.IntentBuilder;

@IntentBuilder
public class DiningMenuActivity extends MvpLceActivity<ViewPager, DayMenu, MvpLceView<DayMenu>, DiningMenuPresenter> {

    @Extra
    DiningLocation location;

    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.mainLayout)
    View mainLayout;

    DiningMenuPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        DiningMenuActivityIntentBuilder.inject(getIntent(), this);
        setTitle(location.getFormalName());
        setupActionBar();
        setupViewPager();
        loadData(false);
    }

    @Override
    public DiningMenuPresenter createPresenter() {
        return new DiningMenuPresenter();
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    private void setupViewPager() {
        adapter = new DiningMenuPagerAdapter(getSupportFragmentManager(), new ArrayList<Meal>());
        contentView.setAdapter(adapter);
        tabLayout.setupWithViewPager(contentView);
    }

    public int getLayoutRes() {
        return R.layout.activity_dining_menu;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_map:
                launchMap();
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected String getErrorMessage(Throwable throwable, boolean pullToRefresh) {
        return DiningServiceHelper.getErrorMessage(this, throwable);
    }

    @Override
    public void setData(DayMenu data) {
        adapter.setMeals(data.getOpenMeals());
        tabLayout.removeAllTabs();
        contentView.removeAllViews();
        adapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(contentView);
        if (adapter.getCount() > 3) {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        }
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(location, pullToRefresh);
    }

    private void launchMap() {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        String name = Uri.encode(location.getFullName());
        Uri geoLocation = Uri.parse(String.format("geo:0,0?q=%f,%f(%s)", lat, lon, name));

        Intent intent = new Intent(Intent.ACTION_VIEW).setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Snackbar.make(mainLayout, R.string.no_app_error, Snackbar.LENGTH_SHORT).show();
    }
}
