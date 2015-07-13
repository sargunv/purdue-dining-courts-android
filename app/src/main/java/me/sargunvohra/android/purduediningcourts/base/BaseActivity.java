package me.sargunvohra.android.purduediningcourts.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import icepick.Icepick;
import me.sargunvohra.android.purduediningcourts.DaggerModule;
import timber.log.Timber;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected Bus bus;

    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inflate layout
        setContentView(getLayoutRes());

        // inject dependencies
        DaggerModule.getObjectGraph().inject(this);

        // inject views
        ButterKnife.inject(this);

        // setup logging
        Timber.uprootAll();
        Timber.plant(new Timber.DebugTree());

        // register event bus
        bus.register(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    public abstract int getLayoutRes();
}