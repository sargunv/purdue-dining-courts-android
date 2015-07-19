package me.sargunvohra.android.purduediningcourts.page.setting;

import android.os.Bundle;
import android.view.View;

import com.hannesdorfmann.fragmentargs.annotation.FragmentArgsInherited;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.base.MainFragment;
import me.sargunvohra.android.purduediningcourts.page.about.AboutView;

@FragmentArgsInherited
public class SettingFragment extends MainFragment<MvpView, MvpBasePresenter<MvpView>> implements AboutView {

    @Override
    public MvpBasePresenter<MvpView> createPresenter() {
        return new MvpBasePresenter<>();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.frame, new PreferenceFragment())
                .commit();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_frame;
    }

    public static class PreferenceFragment extends android.preference.PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
