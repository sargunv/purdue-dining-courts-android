package me.sargunvohra.android.diningcourts.main;

import android.app.ActivityManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import me.sargunvohra.android.diningcourts.R;
import me.sargunvohra.android.diningcourts.util.PlaceholderFragmentBuilder;
import me.sargunvohra.android.diningcourts.util.ToolbarColorAnimator;

public class MainActivity extends AppCompatActivity implements OnTabSelectListener {

    private ToolbarColorAnimator toolbarColorAnimator;
    private SparseArray<TopLevelTab> topLevelTabs;

    private FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarColorAnimator = new ToolbarColorAnimator(toolbar, Color.BLACK);

        topLevelTabs = new SparseArray<>();
        topLevelTabs.put(R.id.tab_dining, new DiningTab());
        topLevelTabs.put(R.id.tab_restaurants, new RestaurantsTab());
        topLevelTabs.put(R.id.tab_settings, new SettingsTab());

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(this);
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        TopLevelTab tab = topLevelTabs.get(tabId);
        if (tab == null)
            throw new IllegalStateException("Unhandled bottom bar tab!");

        int tabColor = ContextCompat.getColor(this, tab.getPrimaryColor());
        int tabColorDark = ContextCompat.getColor(this, tab.getPrimaryDarkColor());

        toolbarColorAnimator.animateToColor(tabColor);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentFrame, tab.buildFragment())
                .commit();

        ActivityManager.TaskDescription tDesc = new ActivityManager.TaskDescription(
                getString(R.string.app_name),
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher),
                tabColorDark);
        setTaskDescription(tDesc);
    }

    private interface TopLevelTab {
        @ColorRes int getPrimaryColor();
        @ColorRes int getPrimaryDarkColor();
        Fragment buildFragment();
    }

    private class DiningTab implements TopLevelTab {
        @Override
        public int getPrimaryColor() {
            return R.color.primary_dining;
        }

        @Override
        public int getPrimaryDarkColor() {
            return R.color.primary_dining_task;
        }

        @Override
        public Fragment buildFragment() {
            return new PlaceholderFragmentBuilder("Dining Courts").build();
        }
    }

    private class RestaurantsTab implements TopLevelTab {
        @Override
        public int getPrimaryColor() {
            return R.color.primary_restaurants;
        }

        @Override
        public int getPrimaryDarkColor() {
            return R.color.primary_restaurants_task;
        }

        @Override
        public Fragment buildFragment() {
            return new PlaceholderFragmentBuilder("Restaurants").build();
        }
    }

    private class SettingsTab implements TopLevelTab {
        @Override
        public int getPrimaryColor() {
            return R.color.primary_settings;
        }

        @Override
        public int getPrimaryDarkColor() {
            return R.color.primary_settings_task;
        }

        @Override
        public Fragment buildFragment() {
            return new PlaceholderFragmentBuilder("Settings").build();
        }
    }
}

