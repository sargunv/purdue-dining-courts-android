package me.sargunvohra.android.diningcourts.main;

import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import me.sargunvohra.android.diningcourts.R;

public class MainActivity extends AppCompatActivity implements OnTabSelectListener {

    private ToolbarColorAnimator toolbarColorAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarColorAnimator = new ToolbarColorAnimator(toolbar, Color.BLACK);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(this);
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        @ColorRes int color;
        switch (tabId) {
            case R.id.tab_dining:
                color = R.color.primary_dining;
                break;
            case R.id.tab_restaurants:
                color = R.color.primary_restaurants;
                break;
            case R.id.tab_settings:
                color = R.color.primary_settings;
                break;
            default:
                throw new IllegalStateException("Unhandled bottom bar tab!");
        }
        toolbarColorAnimator.animateToColor(ContextCompat.getColor(MainActivity.this, color));
    }
}

