package tk.paulmburu.daggerandroid_practice.di.main

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import tk.paulmburu.daggerandroid_practice.network.main.MainApi
import tk.paulmburu.daggerandroid_practice.ui.main.posts.PostsRecyclerAdapter


@Module
class MainModule {

    @Provides
    fun provideAdapter():PostsRecyclerAdapter{
        return PostsRecyclerAdapter()
    }

    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }
}