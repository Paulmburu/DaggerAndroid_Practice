package tk.paulmburu.daggerandroid_practice.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import tk.paulmburu.daggerandroid_practice.viewmodels.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}