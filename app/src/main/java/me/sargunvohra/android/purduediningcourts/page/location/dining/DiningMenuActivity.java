package me.sargunvohra.android.purduediningcourts.page.location.dining;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.InjectView;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.base.BaseActivity;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocation;
import se.emilsjolander.intentbuilder.Extra;
import se.emilsjolander.intentbuilder.IntentBuilder;

@IntentBuilder
public class DiningMenuActivity extends BaseActivity {

    @Extra
    DiningLocation location;

    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;

    @InjectView(R.id.viewPager)
    ViewPager viewPager;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DiningMenuActivityIntentBuilder.inject(getIntent(), this);
        setupActionBar();
        setupViewPager();
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
        DiningMenuPagerAdapter adapter = new DiningMenuPagerAdapter(getSupportFragmentManager(), location.getToday());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
    }

    @Override
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
}
