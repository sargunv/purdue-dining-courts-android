package me.sargunvohra.android.purduediningcourts.fragment;


import android.os.Bundle;

import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.presenter.PlaceholderPresenter;
import me.sargunvohra.android.purduediningcourts.view.PlaceholderView;

public class PlaceholderFragment extends BaseFragment<PlaceholderView, PlaceholderPresenter> implements PlaceholderView {

    public static PlaceholderFragment newInstance(String title) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString(BaseFragment.ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {}

    @Override
    public PlaceholderPresenter createPresenter() {
        return new PlaceholderPresenter();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_placeholder;
    }
}

