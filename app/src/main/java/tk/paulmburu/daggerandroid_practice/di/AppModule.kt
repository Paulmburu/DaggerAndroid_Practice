package tk.paulmburu.daggerandroid_practice.di

import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun myString() = "This my string"
}