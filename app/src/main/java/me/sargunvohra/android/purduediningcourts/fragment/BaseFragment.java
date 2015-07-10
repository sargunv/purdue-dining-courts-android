package me.sargunvohra.android.purduediningcourts.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.activity.MainActivity;

public abstract class BaseFragment extends Fragment {
    protected static final String ARG_TITLE = "base_title";

    private String title = "No name";

    @Bind(R.id.toolbar_layout)
    Toolbar toolbar;

    protected MainActivity activity;

    public BaseFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);

        activity = (MainActivity) getActivity();

        toolbar.setTitle(title);
        activity.setToolbar(toolbar);

        return view;
    }

    public abstract int getLayout();
}
