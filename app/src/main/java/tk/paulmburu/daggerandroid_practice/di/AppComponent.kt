package tk.paulmburu.daggerandroid_practice.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import tk.paulmburu.daggerandroid_practice.BaseApplication

@Component
interface AppComponent: AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder
        fun build():AppComponent
    }
}