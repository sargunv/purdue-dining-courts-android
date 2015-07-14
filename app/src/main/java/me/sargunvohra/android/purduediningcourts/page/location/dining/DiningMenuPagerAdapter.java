package me.sargunvohra.android.purduediningcourts.page.location.dining;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.Arrays;
import java.util.List;

import me.sargunvohra.android.purduediningcourts.model.dining.Meal;

public class DiningMenuPagerAdapter extends FragmentStatePagerAdapter {

    private Meal[] meals;

    public DiningMenuPagerAdapter(FragmentManager fm, List<Meal> data) {
        super(fm);
        setMeals(data);
    }

    public void setMeals(List<Meal> data) {
        this.meals = data.toArray(new Meal[data.size()]);
        Arrays.sort(meals);
    }

    @Override
    public Fragment getItem(int position) {
        return new DiningMenuPageFragmentBuilder(meals[position]).build();
    }

    @Override
    public int getCount() {
        return meals.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return meals[position].getName();
    }
}
