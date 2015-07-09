package me.sargunvohra.android.purduediningcourts.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import me.sargunvohra.android.purduediningcourts.DrawerItems;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocation;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocation;
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
                        primaryDrawerItem("Today", R.drawable.ic_today_black_24dp, DrawerItems.TODAY),
                        primaryDrawerItem("Favorites", R.drawable.ic_favorite_black_24dp, DrawerItems.FAVORITES),
                        sectionDrawerItem("Dining Courts", DrawerItems.DINING_SECTION),
                        sectionDrawerItem("Restaurants", DrawerItems.RESTAURANTS_SECTION),
                        sectionDrawerItem("Cafés", DrawerItems.CAFES_SECTION),
                        sectionDrawerItem("Markets", DrawerItems.MARKETS_SECTION),
                        new DividerDrawerItem(),
                        primaryDrawerItem("Settings", R.drawable.ic_settings_black_24dp, DrawerItems.SETTINGS),
                        primaryDrawerItem("Help & About", R.drawable.ic_info_black_24dp, DrawerItems.INFO)
                )
                .build();
    }

    private Map<DrawerItems, Integer> sectionEndId = new HashMap<>();

    void addLocationToMenu(DiningLocation location) {

        int diningIcon = R.drawable.ic_local_dining_black_24dp;
        String name = location.getName();
        int id = Math.abs(name.hashCode());
        SecondaryDrawerItem newItem = secondaryDrawerItem(name, diningIcon, id);

        addSectionItem(DrawerItems.DINING_SECTION, newItem);
    }

    void addLocationToMenu(RetailLocation location) {
        String name = location.getName();
        int id = Math.abs(name.hashCode());

        DrawerItems section;
        int retailIcon;
        switch (location.getType()) {
            case "Markets":
                section = DrawerItems.MARKETS_SECTION;
                retailIcon = R.drawable.ic_store_black_24dp;
                break;
            case "Restaurants":
                section = DrawerItems.RESTAURANTS_SECTION;
                retailIcon = R.drawable.ic_food_black_24dp;
                break;
            case "Cafés":
                section = DrawerItems.CAFES_SECTION;
                retailIcon = R.drawable.ic_local_cafe_black_24dp;
                break;
            default:
                Timber.e("Unknown retail location type: %s", location.getType());
                return;
        }

        SecondaryDrawerItem newItem = secondaryDrawerItem(name, retailIcon, id);

        addSectionItem(section, newItem);
    }

    private void addSectionItem(DrawerItems section, SecondaryDrawerItem item) {
        int parentId;
        if (sectionEndId.get(section) != null)
            parentId = sectionEndId.get(section);
        else
            parentId = section.ordinal();

        int pos = drawer.getPositionFromIdentifier(parentId) + 1;
        drawer.addItem(item, pos);

        sectionEndId.put(section, item.getIdentifier());
    }

    private PrimaryDrawerItem primaryDrawerItem(String name, int iconRes, DrawerItems id) {
        return new PrimaryDrawerItem()
                .withName(name)
                .withIcon(iconRes)
                .withIconTintingEnabled(true)
                .withIdentifier(id.ordinal())
                .withIconColor(Color.argb(0x8c, 0x00, 0x00, 0x00));
    }

    private SectionDrawerItem sectionDrawerItem(String name, DrawerItems id) {
        return new SectionDrawerItem()
                .withName(name)
                .withIdentifier(id.ordinal());
    }

    private SecondaryDrawerItem secondaryDrawerItem(String name, int iconRes, int id) {
        return new SecondaryDrawerItem()
                .withName(name)
                .withIcon(iconRes)
                .withIconTintingEnabled(true)
                .withIdentifier(id)
                .withIconColor(Color.argb(0x8c, 0x00, 0x00, 0x00));
    }
}
