package me.sargunvohra.android.purduediningcourts.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.Bind;
import me.sargunvohra.android.purduediningcourts.DrawerItems;
import me.sargunvohra.android.purduediningcourts.R;
import timber.log.Timber;

public abstract class DrawerActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        buildDrawerItem("Today", R.drawable.ic_today_black_24dp, DrawerItems.TODAY),
                        buildDrawerItem("Favorites", R.drawable.ic_favorite_black_24dp, DrawerItems.FAVORITES),
                        new DividerDrawerItem(),
                        buildDrawerItem("Dining Courts", R.drawable.ic_local_dining_black_24dp, DrawerItems.DINING),
                        buildDrawerItem("Restaurants", R.drawable.ic_food_black_24dp, DrawerItems.RESTAURANTS),
                        buildDrawerItem("Cafés", R.drawable.ic_local_cafe_black_24dp, DrawerItems.CAFES),
                        buildDrawerItem("Markets", R.drawable.ic_store_black_24dp, DrawerItems.MARKETS),
                        new DividerDrawerItem(),
                        buildDrawerItem("Settings", R.drawable.ic_settings_black_24dp, DrawerItems.SETTINGS),
                        buildDrawerItem("About", R.drawable.ic_info_black_24dp, DrawerItems.ABOUT)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        return onItemSelected(drawerItem);
                    }
                })
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onItemSelected(drawer.getDrawerItems().get(drawer.getCurrentSelection()));
    }

    private boolean onItemSelected(IDrawerItem item) {
        Timber.d("selected %s", item);
        int itemId = item.getIdentifier();
        onDrawerItemSelected(DrawerItems.values()[itemId]);
        return false;
    }

    abstract void onDrawerItemSelected(DrawerItems item);

    private PrimaryDrawerItem buildDrawerItem(String name, int iconRes, DrawerItems id) {
        return new PrimaryDrawerItem()
                .withName(name)
                .withIcon(iconRes)
                .withIconTintingEnabled(true)
                .withIdentifier(id.ordinal())
                .withIconColor(Color.argb(0x8c, 0x00, 0x00, 0x00));
    }
}
