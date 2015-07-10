package me.sargunvohra.android.purduediningcourts.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mikepenz.aboutlibraries.LibsBuilder;

import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.presenter.AboutPresenter;
import me.sargunvohra.android.purduediningcourts.view.AboutView;

public class AboutFragment extends BaseFragment<AboutView, AboutPresenter> implements AboutView {

    public static AboutFragment newInstance(Context ctx) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(BaseFragment.ARG_TITLE, ctx.getString(R.string.nav_about));
        fragment.setArguments(args);
        return fragment;
    }

    public AboutFragment() {}

    @Override
    public AboutPresenter createPresenter() {
        return new AboutPresenter();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.about_frame, buildAboutFragment())
                .commit();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_about;
    }

    private Fragment buildAboutFragment() {
        return new LibsBuilder()
                .withFields(R.string.class.getFields())
                .withAutoDetect(true)
                .withListener(getPresenter())
                .withAnimations(false)
                .withLicenseDialog(true)
                .withLicenseShown(true)
                .fragment();
    }
}
