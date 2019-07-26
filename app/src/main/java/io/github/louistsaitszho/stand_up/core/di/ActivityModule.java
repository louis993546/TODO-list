package io.github.louistsaitszho.stand_up.core.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.github.louistsaitszho.stand_up.core.MainActivity;

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity contributeMainActivityInjector();
}
