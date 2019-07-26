package io.github.louistsaitszho.stand_up;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {AndroidInjectionModule.class, ActivityModule.class})
interface AppComponent {
    void inject(App app);
}
