package io.github.louistsaitszho.stand_up.core.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.github.louistsaitszho.stand_up.feature_task_list.MainFragment;

@Module
interface MainActivityModule {
    @ContributesAndroidInjector
    MainFragment contributeMainFragment();
}
