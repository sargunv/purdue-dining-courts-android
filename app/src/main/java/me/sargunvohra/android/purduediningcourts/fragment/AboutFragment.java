package me.sargunvohra.android.purduediningcourts.fragment;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.activity.MainActivity;

public class AboutFragment extends Fragment {
    private static final String ARG_TITLE = "title";

    private String title;

    @Bind(R.id.toolbar_layout)
    Toolbar toolbar;

    public static AboutFragment newInstance(String title) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public AboutFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);

        MainActivity activity = (MainActivity) getActivity();

        toolbar.setTitle(title);
        activity.setToolbar(toolbar);

        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.about_frame, AboutFragmentBuilder.build())
                .commit();

        return view;
    }


}
