package io.github.louistsaitszho.stand_up.core;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import io.github.louistsaitszho.stand_up.core.di.DaggerAppComponent;


public class App extends Application implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initThreeTen();
        initDagger();
    }

    private void initThreeTen() {
        AndroidThreeTen.init(this);
    }

    private void initDagger() {
        DaggerAppComponent.create().inject(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
