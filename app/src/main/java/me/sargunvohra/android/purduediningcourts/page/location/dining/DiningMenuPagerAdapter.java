package me.sargunvohra.android.purduediningcourts.page.location.dining;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lombok.Getter;
import lombok.Setter;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningDay;

public class DiningMenuPagerAdapter extends FragmentStatePagerAdapter {

    @Getter
    @Setter
    private DiningDay day;

    public DiningMenuPagerAdapter(FragmentManager fm, DiningDay data) {
        super(fm);
        this.day = data;
    }

    @Override
    public Fragment getItem(int position) {
        return new DiningMenuPageFragmentBuilder().build();
    }

    @Override
    public int getCount() {
        return day.getMeals().size();
    }
}
