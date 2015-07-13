package me.sargunvohra.android.purduediningcourts.base;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import butterknife.InjectView;
import me.sargunvohra.android.purduediningcourts.MainActivity;
import me.sargunvohra.android.purduediningcourts.R;

public abstract class MainFragment<IView extends MvpView, Presenter extends MvpPresenter<IView>> extends MvpFragment<IView, Presenter> {
    @Arg
    public String title;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(title);
        ((MainActivity) getActivity()).setToolbar(toolbar);
    }
}
