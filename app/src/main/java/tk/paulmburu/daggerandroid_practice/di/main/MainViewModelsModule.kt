package tk.paulmburu.daggerandroid_practice.di.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tk.paulmburu.daggerandroid_practice.di.ViewModelKey
import tk.paulmburu.daggerandroid_practice.ui.main.profile.ProfileViewModel

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

}