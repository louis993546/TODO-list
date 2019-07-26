package io.github.louistsaitszho.stand_up;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class App extends Application {

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
        // TODO
    }

}
