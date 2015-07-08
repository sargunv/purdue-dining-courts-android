package me.sargunvohra.android.purduediningcourts.activity;

import android.support.v7.app.AppCompatActivity;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import me.sargunvohra.android.purduediningcourts.DaggerModule;
import timber.log.Timber;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected Bus bus;

    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inject dependencies
        DaggerModule.getObjectGraph().inject(this);

        // setup logging
        Timber.plant(new Timber.DebugTree());

        // register event bus
        bus.register(this);
    }
}