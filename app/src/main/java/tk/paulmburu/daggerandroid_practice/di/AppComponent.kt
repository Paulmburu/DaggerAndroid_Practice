package tk.paulmburu.daggerandroid_practice.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import tk.paulmburu.daggerandroid_practice.BaseApplication
import tk.paulmburu.daggerandroid_practice.SessionManager
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent: AndroidInjector<BaseApplication> {

    val sessionManager: SessionManager

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder
        fun build():AppComponent
    }
}