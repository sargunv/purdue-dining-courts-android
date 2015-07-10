package me.sargunvohra.android.purduediningcourts.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.aboutlibraries.LibsConfiguration;
import com.mikepenz.aboutlibraries.entity.Library;

import me.sargunvohra.android.purduediningcourts.R;

public class AboutFragment extends BaseFragment implements LibsConfiguration.LibsListener {

    public static AboutFragment newInstance(Context ctx) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(BaseFragment.ARG_TITLE, ctx.getString(R.string.nav_about));
        fragment.setArguments(args);
        return fragment;
    }

    public AboutFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.about_frame, buildAboutFragment())
                .commit();

        return view;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_about;
    }

    private Fragment buildAboutFragment() {
        return new LibsBuilder()
                .withFields(R.string.class.getFields())
                .withAutoDetect(true)
                .withListener(this)
                .withAnimations(false)
                .withLicenseDialog(true)
                .withLicenseShown(true)
                .fragment();
    }

    @Override
    public boolean onExtraClicked(View view, Libs.SpecialButton specialButton) {
        String url = null;
        switch (specialButton) {
            case SPECIAL1:
                url = view.getContext().getString(R.string.aboutLibraries_description_special1_text);
                break;
            case SPECIAL2:
                url = view.getContext().getString(R.string.aboutLibraries_description_special2_text);
                break;
            case SPECIAL3:
                url = view.getContext().getString(R.string.aboutLibraries_description_special3_text);
                break;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(browserIntent);
        return true;
    }

    @Override
    public void onIconClicked(View view) {}

    @Override
    public boolean onLibraryAuthorClicked(View view, Library library) {
        return false;
    }

    @Override
    public boolean onLibraryContentClicked(View view, Library library) {
        return false;
    }

    @Override
    public boolean onLibraryBottomClicked(View view, Library library) {
        return false;
    }

    @Override
    public boolean onIconLongClicked(View view) {
        return false;
    }

    @Override
    public boolean onLibraryAuthorLongClicked(View view, Library library) {
        return false;
    }

    @Override
    public boolean onLibraryContentLongClicked(View view, Library library) {
        return false;
    }

    @Override
    public boolean onLibraryBottomLongClicked(View view, Library library) {
        return false;
    }
}
