package tk.paulmburu.daggerandroid_practice.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun myString() = "This my string"

    @Provides
    fun getApp(application: Application): Boolean{
        return application != null
    }
}