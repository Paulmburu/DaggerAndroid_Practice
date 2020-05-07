package tk.paulmburu.daggerandroid_practice.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tk.paulmburu.daggerandroid_practice.ui.auth.AuthActivity

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeAuthInjector(): AuthActivity
}