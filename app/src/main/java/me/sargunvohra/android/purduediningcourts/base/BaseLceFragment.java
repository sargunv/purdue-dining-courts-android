package me.sargunvohra.android.purduediningcourts.base;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import butterknife.InjectView;
import me.sargunvohra.android.purduediningcourts.MainActivity;
import me.sargunvohra.android.purduediningcourts.R;
import retrofit.RetrofitError;
import timber.log.Timber;

public abstract class BaseLceFragment<ContentView extends View, Model, MvpView extends MvpLceView<Model>, Presenter extends MvpPresenter<MvpView>> extends MvpLceFragment<ContentView, Model, MvpView, Presenter> {
    protected static final String ARG_TITLE = "base_title";

    String title = "No name";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    public BaseLceFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(title);
        ((MainActivity) getActivity()).setToolbar(toolbar);
    }

    public String getErrorMessage(Throwable e, boolean pullToRefresh) {
        if (e instanceof RetrofitError) {
            RetrofitError.Kind kind = ((RetrofitError)e).getKind();
            switch (kind) {
                case NETWORK:
                    return getActivity().getString(R.string.network_error);
                case CONVERSION:
                    return getActivity().getString(R.string.conversion_error);
                case HTTP:
                    return getActivity().getString(R.string.http_error);
                case UNEXPECTED:
                    break;
                default:
                    Timber.wtf("Encountered unknown error type: %s", kind);
            }
        }
        return getActivity().getString(R.string.unexpected_error);
    }
}
