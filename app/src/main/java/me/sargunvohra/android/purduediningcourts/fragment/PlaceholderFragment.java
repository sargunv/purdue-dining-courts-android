package me.sargunvohra.android.purduediningcourts.fragment;


import android.os.Bundle;

import me.sargunvohra.android.purduediningcourts.R;

public class PlaceholderFragment extends BaseFragment {

    public static PlaceholderFragment newInstance(String title) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString(BaseFragment.ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {}

    @Override
    public int getLayout() {
        return R.layout.fragment_placeholder;
    }
}
