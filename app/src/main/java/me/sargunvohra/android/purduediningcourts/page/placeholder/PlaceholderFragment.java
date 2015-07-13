package me.sargunvohra.android.purduediningcourts.page.placeholder;


import com.hannesdorfmann.fragmentargs.annotation.FragmentArgsInherited;

import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.base.MainFragment;

@FragmentArgsInherited
public class PlaceholderFragment extends MainFragment<PlaceholderView, PlaceholderPresenter> implements PlaceholderView {

    @Override
    public PlaceholderPresenter createPresenter() {
        return new PlaceholderPresenter();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_placeholder;
    }
}

