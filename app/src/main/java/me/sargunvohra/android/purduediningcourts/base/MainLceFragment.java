package me.sargunvohra.android.purduediningcourts.base;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import butterknife.InjectView;
import me.sargunvohra.android.purduediningcourts.MainActivity;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.service.DiningServiceHelper;

public abstract class MainLceFragment<ContentView extends View, Model, MvpView extends MvpLceView<Model>, Presenter extends MvpPresenter<MvpView>> extends MvpLceFragment<ContentView, Model, MvpView, Presenter> {
    protected static final String ARG_TITLE = "base_title";

    @Arg
    public String title = "No name";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(title);
        ((MainActivity) getActivity()).setToolbar(toolbar);
    }

    @Override
    public String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return DiningServiceHelper.getErrorMessage(getActivity(), e);
    }
}
