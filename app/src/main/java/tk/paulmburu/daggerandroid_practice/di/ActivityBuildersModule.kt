package tk.paulmburu.daggerandroid_practice.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tk.paulmburu.daggerandroid_practice.di.auth.AuthModule
import tk.paulmburu.daggerandroid_practice.di.auth.AuthViewModelModule
import tk.paulmburu.daggerandroid_practice.ui.auth.AuthActivity
import tk.paulmburu.daggerandroid_practice.ui.main.MainActivity

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [AuthViewModelModule::class, AuthModule::class]
    )
    abstract fun contributeAuthInjector(): AuthActivity

    @ContributesAndroidInjector
    abstract fun contrbuteMainInjector(): MainActivity
}