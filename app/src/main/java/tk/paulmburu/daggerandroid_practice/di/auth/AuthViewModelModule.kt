package tk.paulmburu.daggerandroid_practice.di.auth

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tk.paulmburu.daggerandroid_practice.di.ViewModelKey
import tk.paulmburu.daggerandroid_practice.ui.auth.AuthViewModel

@Module
abstract class AuthViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}