package me.sargunvohra.android.purduediningcourts.page.location.dining;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
}
