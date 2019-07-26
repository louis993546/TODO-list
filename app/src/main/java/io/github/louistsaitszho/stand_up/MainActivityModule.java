package io.github.louistsaitszho.stand_up;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
interface MainActivityModule {
    @ContributesAndroidInjector
    MainFragment contributeMainFragment();
}
