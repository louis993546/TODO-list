package io.github.louistsaitszho.stand_up.core;

import androidx.multidex.MultiDexApplication;

import com.jakewharton.threetenabp.AndroidThreeTen;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import io.github.louistsaitszho.stand_up.core.di.DaggerAppComponent;
import timber.log.Timber;


public class App extends MultiDexApplication implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
        initThreeTen();
        initDagger();
    }

    private void initTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    private void initThreeTen() {
        AndroidThreeTen.init(this);
    }

    private void initDagger() {
        DaggerAppComponent.builder()
                .applicationContext(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

}
