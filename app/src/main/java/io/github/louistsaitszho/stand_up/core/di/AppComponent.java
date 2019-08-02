package io.github.louistsaitszho.stand_up.core.di;

import android.content.Context;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import io.github.louistsaitszho.stand_up.core.App;
import io.github.louistsaitszho.stand_up.core.data.DataModule;

@Component(modules = {
        AndroidInjectionModule.class,
        ActivityModule.class,
        DataModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder applicationContext(Context context);

        AppComponent build();
    }

    void inject(App app);

}
