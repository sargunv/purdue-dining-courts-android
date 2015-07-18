package me.sargunvohra.android.purduediningcourts.page.location.retail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocation;
import me.sargunvohra.android.purduediningcourts.service.DiningServiceHelper;
import se.emilsjolander.intentbuilder.Extra;
import se.emilsjolander.intentbuilder.IntentBuilder;

@IntentBuilder
public class RetailInfoActivity extends MvpActivity<MvpView, MvpPresenter<MvpView>> {//MvpLceActivity<NestedScrollView, RetailLocation, MvpLceView<RetailLocation>, MvpBasePresenter<MvpLceView<RetailLocation>>> {

    @Extra
    RetailLocation location;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @InjectView(R.id.toolbarImage)
    ImageView toolbarImage;

    @InjectView(R.id.mainLayout)
    View mainLayout;

    @InjectView(R.id.retailDescription)
    TextView retailDescription;

    @InjectView(R.id.retailLogo)
    ImageView retailLogo;

    @InjectView(R.id.retailMenuButton)
    Button retailMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        RetailInfoActivityIntentBuilder.inject(getIntent(), this);
        setTitle(location.getFullName());
        setupActionBar();
        setupContent();
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setTitle(location.getFullName());

        // setup back button
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    private void setupContent() {
        // description
        retailDescription.setText(location.getDescription());

        // header image
        Picasso.with(toolbarImage.getContext())
                .load(DiningServiceHelper.getFileUrl(location.getTileImage()))
                .placeholder(R.drawable.placeholder)
                .resize(1920, 1080)
                .centerCrop()
                .into(toolbarImage);

        // logo image
        Picasso.with(retailLogo.getContext())
                .load(DiningServiceHelper.getFileUrl(location.getLogo()))
                .into(retailLogo);

        // menu button
        retailMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(mainLayout, location.getMenuUrl(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public int getLayoutRes() {
        return R.layout.activity_retail_info;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_map:
                launchMap();
                break;
            case R.id.menu_call:
                callLocation();
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void launchMap() {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        String name = Uri.encode(location.getFullName());
        Uri geoLocation = Uri.parse(String.format("geo:0,0?q=%f,%f(%s)", lat, lon, name));

        Intent intent = new Intent(Intent.ACTION_VIEW).setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Snackbar.make(mainLayout, R.string.no_app_error, Snackbar.LENGTH_SHORT).show();
    }

    private void callLocation() {
        String number = location.getPhoneNumber().replaceAll("[^0-9]+", "");
        Uri phoneNumber = Uri.parse(String.format("tel:%s", number));

        Intent intent = new Intent(Intent.ACTION_DIAL).setData(phoneNumber);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Snackbar.make(mainLayout, R.string.no_app_error, Snackbar.LENGTH_SHORT).show();
    }

//    @Override
//    protected String getErrorMessage(Throwable throwable, boolean b) {
//        return "lol error";
//    }

//    @Override
//    public void setData(RetailLocation data) {}

//    @Override
//    public void loadData(boolean pullToRefresh) {}

    @Override
    public MvpPresenter<MvpView> createPresenter() {
        return new MvpBasePresenter<>();
    }
}
