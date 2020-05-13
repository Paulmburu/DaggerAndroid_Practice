package tk.paulmburu.daggerandroid_practice.di.main

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import tk.paulmburu.daggerandroid_practice.network.main.MainApi


@Module
class MainModule {

    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }
}