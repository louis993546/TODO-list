package io.github.louistsaitszho.stand_up.core.di;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import io.github.louistsaitszho.stand_up.core.App;
import io.github.louistsaitszho.stand_up.core.data.local.DatabaseModule;

@Component(modules = {
        AndroidInjectionModule.class,
        DatabaseModule.class,
        ActivityModule.class
})
public interface AppComponent {
    void inject(App app);
}
