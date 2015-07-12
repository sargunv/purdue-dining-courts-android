package me.sargunvohra.android.purduediningcourts.base.lce;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import lombok.Getter;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.base.BaseFragment;
import me.sargunvohra.android.purduediningcourts.base.BaseListAdapter;
import retrofit.RetrofitError;
import timber.log.Timber;

public abstract class LceFragment<ListItem, Wrapper, Adapter extends BaseListAdapter<ListItem, ? extends BaseListAdapter.ViewHolder>> extends BaseFragment<LceView<ListItem>, LcePresenter<ListItem, Wrapper>> implements LceView<ListItem>, BaseListAdapter.OnClickListener<ListItem> {

    @Getter
    @InjectView(R.id.contentView)
    RecyclerView contentView;

    @Getter
    @InjectView(R.id.loadingView)
    View loadingView;

    @Getter
    @InjectView(R.id.errorView)
    View errorView;

    @Getter
    @InjectView(R.id.errorMessage)
    TextView errorMessage;

    Adapter adapter;

    public LceFragment() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = buildAdapter();
        contentView.setLayoutManager(buildLayoutManager());
        contentView.setAdapter(adapter);
        loadData();
    }

    public abstract Adapter buildAdapter();

    public abstract RecyclerView.LayoutManager buildLayoutManager();

    @Override
    public void showLoading() {
        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent() {
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(RetrofitError.Kind kind) {
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);

        switch (kind) {
            case NETWORK:
                errorMessage.setText(R.string.network_error);
                break;
            case CONVERSION:
                errorMessage.setText(R.string.conversion_error);
                break;
            case HTTP:
                errorMessage.setText(R.string.http_error);
                break;
            default:
                Timber.wtf("Encountered unknown error type: %s", kind);
            case UNEXPECTED:
                errorMessage.setText(R.string.unexpected_error);
                break;
        }
    }

    @Override
    public void setData(List<ListItem> data) {
        adapter.setDataSet(data);
    }

    @OnClick(R.id.retryButton)
    public void loadData() {
        presenter.loadData();
    }
}

