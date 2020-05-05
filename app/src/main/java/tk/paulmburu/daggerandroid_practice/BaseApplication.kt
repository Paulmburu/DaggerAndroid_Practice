package tk.paulmburu.daggerandroid_practice

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import tk.paulmburu.daggerandroid_practice.di.DaggerAppComponent

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerAppComponent.builder().application(this).build()
    }
}