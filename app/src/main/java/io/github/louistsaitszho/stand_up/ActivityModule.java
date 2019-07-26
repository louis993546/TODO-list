package io.github.louistsaitszho.stand_up;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity contributeMainActivityInjector();
}
