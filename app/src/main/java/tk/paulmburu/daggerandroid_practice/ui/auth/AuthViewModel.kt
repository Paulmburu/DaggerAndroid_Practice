package tk.paulmburu.daggerandroid_practice.ui.auth

import android.util.Log
import androidx.lifecycle.*
import io.reactivex.Observer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import tk.paulmburu.daggerandroid_practice.SessionManager
import tk.paulmburu.daggerandroid_practice.models.User
import tk.paulmburu.daggerandroid_practice.network.auth.AuthApi
import java.lang.Exception
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi, sessionManager: SessionManager) : ViewModel() {
    val TAG = "AuthViewModel"
    var thisAuthApi = authApi
    var sessionManager = sessionManager

    var authUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun authenticateWitId(userId: Int) {
        Log.d(TAG,"AuthenticateWithUser-> Attempting to login")

        sessionManager.authenticateWithId(queryUserId(userId))
    }

    fun queryUserId(userId: Int): LiveData<AuthResource<User>>{
        return LiveDataReactiveStreams.fromPublisher(
            thisAuthApi.getUser(userId)
                // instead of calling on error (error happens)
                .onErrorReturn(Function<Throwable, User> { it ->
                    Log.d(TAG," NGORIIIIIIIII")
                    return@Function User(-1, "", "", "")
                })
                .map(Function<User, AuthResource<User>> { it ->
                    if (it.id == -1) {
                        return@Function AuthResource.error("Could not Authenticate", User(0,"","",""))
                    }
                    return@Function AuthResource.authenticated(it)
                })
                .subscribeOn(Schedulers.io())
        )
    }

    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }


    init {
        Log.d(TAG, "AuthViewModel: viewmodel is working")
        getUser()
    }

    fun getUser() {
        Log.d(TAG, "AuthViewModel: get user")
        thisAuthApi.getUser(1)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.d(TAG, "onNext =  ${it.email}")
            }
    }

    fun checkAuthApiNull() {
        if (thisAuthApi == null) {
            Log.d(TAG, "AuthViewModel: auth api is NULL")
        } else {
            Log.d(TAG, "AuthViewModel: auth api is NOT NULL")
        }
    }
}