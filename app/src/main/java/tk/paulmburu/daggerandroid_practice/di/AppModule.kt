package tk.paulmburu.daggerandroid_practice.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import tk.paulmburu.daggerandroid_practice.R
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideRequestOptions() : RequestOptions{
        return RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager{
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun proidesAppDrawable(application: Application): Drawable {
        return ContextCompat.getDrawable(application,R.drawable.gopher)!!
    }
}