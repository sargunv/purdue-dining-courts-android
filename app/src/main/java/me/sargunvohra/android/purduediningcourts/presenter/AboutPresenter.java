package me.sargunvohra.android.purduediningcourts.presenter;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsConfiguration;
import com.mikepenz.aboutlibraries.entity.Library;

import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.view.AboutView;

public class AboutPresenter implements MvpPresenter<AboutView>, LibsConfiguration.LibsListener {
    @Override
    public void attachView(AboutView view) {}

    @Override
    public void detachView(boolean retainInstance) {}

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