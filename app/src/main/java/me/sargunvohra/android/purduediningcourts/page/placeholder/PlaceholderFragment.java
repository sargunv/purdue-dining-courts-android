package me.sargunvohra.android.purduediningcourts.page.placeholder;


import android.os.Bundle;

import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.base.BaseFragment;

public class PlaceholderFragment extends BaseFragment<PlaceholderView, PlaceholderPresenter> implements PlaceholderView {

    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(String title) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString(BaseFragment.ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public PlaceholderPresenter createPresenter() {
        return new PlaceholderPresenter();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_placeholder;
    }
}

