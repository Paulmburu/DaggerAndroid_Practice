package tk.paulmburu.daggerandroid_practice.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tk.paulmburu.daggerandroid_practice.di.auth.AuthModule
import tk.paulmburu.daggerandroid_practice.di.auth.AuthScope
import tk.paulmburu.daggerandroid_practice.di.auth.AuthViewModelModule
import tk.paulmburu.daggerandroid_practice.di.main.MainFragmentBuildersModule
import tk.paulmburu.daggerandroid_practice.di.main.MainModule
import tk.paulmburu.daggerandroid_practice.di.main.MainScope
import tk.paulmburu.daggerandroid_practice.di.main.MainViewModelsModule
import tk.paulmburu.daggerandroid_practice.ui.auth.AuthActivity
import tk.paulmburu.daggerandroid_practice.ui.main.MainActivity

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthViewModelModule::class, AuthModule::class]
    )
    abstract fun contributeAuthInjector(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, MainModule::class]
    )
    abstract fun contrbuteMainInjector(): MainActivity
}