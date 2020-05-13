package tk.paulmburu.daggerandroid_practice.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tk.paulmburu.daggerandroid_practice.SessionManager
import tk.paulmburu.daggerandroid_practice.models.User
import tk.paulmburu.daggerandroid_practice.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(sessionManager: SessionManager): ViewModel() {
    val TAG = "ProfileViewModel"
    var sessionManager = sessionManager

    init {
        Log.d(TAG,"ProfileViewModel: viewmodel is ready... ")
    }

    fun getAuthenticateUser(): LiveData<AuthResource<User>>{
        return sessionManager.getAuthUser()
    }

}