package tk.paulmburu.daggerandroid_practice.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import tk.paulmburu.daggerandroid_practice.network.auth.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {
    val TAG = "AuthViewModel"
    var thisApi = authApi


    init {
        Log.d(TAG,"AuthViewModel: viewmodel is working")
    }

    fun checkAuthApiNull(){
        if (thisApi == null){
            Log.d(TAG,"AuthViewModel: auth api is NULL")
        }else{
            Log.d(TAG,"AuthViewModel: auth api is NOT NULL")
        }
    }
}