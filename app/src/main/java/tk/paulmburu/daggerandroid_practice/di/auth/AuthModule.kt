package tk.paulmburu.daggerandroid_practice.di.auth

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import tk.paulmburu.daggerandroid_practice.network.auth.AuthApi

@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

}