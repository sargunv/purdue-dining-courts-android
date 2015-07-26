package me.sargunvohra.android.purduediningcourts.page.location.dining;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.Collections;
import java.util.List;

import butterknife.InjectView;
import icepick.Icicle;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.model.dining.item.Allergen;
import me.sargunvohra.android.purduediningcourts.model.dining.item.Item;
import me.sargunvohra.android.purduediningcourts.model.dining.item.NutritionFact;
import me.sargunvohra.android.purduediningcourts.service.DiningServiceHelper;
import me.sargunvohra.android.purduediningcourts.service.StorageHelper;
import se.emilsjolander.intentbuilder.Extra;
import se.emilsjolander.intentbuilder.IntentBuilder;

@IntentBuilder
public class DiningItemActivity extends MvpLceActivity<ViewPager, Item, MvpLceView<Item>, DiningItemPresenter> {

    @Extra
    String itemId;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.mainLayout)
    View mainLayout;

    @InjectView(R.id.itemTitle)
    TextView itemTitle;

    @InjectView(R.id.itemVeg)
    TextView itemVeg;

    @InjectView(R.id.ingredients)
    TextView ingredients;

    @InjectView(R.id.allergens)
    TextView allergens;

    @InjectView(R.id.nutrition)
    TextView nutrition;

    @Icicle
    Item data;

    StorageHelper storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        storage = new StorageHelper(this);
        setTitle("");
        DiningItemActivityIntentBuilder.inject(getIntent(), this);
        setupActionBar();
        if (data != null) {
            setData(data);
            showContent();
        } else {
            loadData(false);
        }
    }

    @Override
    public DiningItemPresenter createPresenter() {
        return new DiningItemPresenter();
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    public int getLayoutRes() {
        return R.layout.activity_dining_item;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_favorite:
                if (data != null) {
                    if (storage.isFavorite(data.getID()))
                        storage.unsetFavorite(data);
                    else
                        storage.setFavorite(data);
                    updateFavoriteIcon(item);
                    String message = getString(item.isChecked() ? R.string.favorite_add : R.string.favorite_remove);
                    message = String.format(message, data.getName());
                    Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
                }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        updateFavoriteIcon(menu.findItem(R.id.menu_favorite));
        return true;
    }

    private void updateFavoriteIcon(MenuItem item) {
        boolean favorite = storage.isFavorite(itemId);
        item.setChecked(favorite);
        if (favorite)
            item.setIcon(R.drawable.ic_star_white_24dp);
        else
            item.setIcon(R.drawable.ic_star_border_white_24dp);
    }

    @Override
    protected String getErrorMessage(Throwable throwable, boolean pullToRefresh) {
        return DiningServiceHelper.getErrorMessage(this, throwable);
    }

    @Override
    public void setData(Item data) {
        this.data = data;

        applyTitle(data.getName());
        applyVegetarian(data.getIsVegetarian());
        applyNutrition(data.getNutrition());
        applyAllergens(data.getAllergens());
        applyIngredients(data.getIngredients());
    }

    private void applyNutrition(List<NutritionFact> nutritionFactList) {
        if (nutritionFactList == null || nutritionFactList.size() <= 0) {
            nutrition.setText(R.string.noinfo);
        } else {
            Collections.sort(nutritionFactList);
            StringBuilder str = new StringBuilder();
            for (NutritionFact n : nutritionFactList) {
                String name = n.getName();
                String value = n.getLabelValue();
                if (value == null && n.getValue() != null)
                    value = n.getValue().toString();
                if (n.getDailyValue() != null && n.getDailyValue().trim().length() > 0)
                    value += String.format(" (%s)", n.getDailyValue());
                if (value != null)
                    str.append(name).append(": ").append(value.trim()).append("\n");
            }
            if (str.length() <= 0) {
                nutrition.setText(R.string.noinfo);
            } else {
                nutrition.setText(str.delete(str.length()-1, str.length()).toString());
            }
        }
    }

    private void applyAllergens(List<Allergen> allergenList) {
        if (allergenList == null || allergenList.size() <= 0) {
            allergens.setText(R.string.noinfo);
        } else {
            StringBuilder str = new StringBuilder();
            for (Allergen a : allergenList) {
                if (a.getValue()) {
                    str.append(a.getName()).append(", ");
                }
            }
            if (str.length() <= 0) {
                allergens.setText(R.string.unknownAllergens);
            } else {
                allergens.setText(str.delete(str.length()-2, str.length()).toString());
            }
        }
    }

    private void applyIngredients(String ingredientsStr) {
        if (ingredientsStr == null || ingredientsStr.trim().length() <= 0) {
            ingredients.setText(R.string.noinfo);
        } else {
            ingredients.setText(ingredientsStr);
        }
    }

    private void applyVegetarian(Boolean isVegetarian) {
        if (isVegetarian) {
            itemVeg.setText(R.string.veg);
            itemVeg.setTextColor(getResources().getColor(R.color.vegetarian));
        } else {
            itemVeg.setText(R.string.nonveg);
        }
    }

    private void applyTitle(String title) {
        setTitle(title);
        itemTitle.setText(title);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(itemId, pullToRefresh);
    }
}
