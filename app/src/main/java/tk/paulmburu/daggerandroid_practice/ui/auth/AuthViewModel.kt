package tk.paulmburu.daggerandroid_practice.ui.auth

import android.util.Log
import androidx.lifecycle.*
import io.reactivex.Observer
import io.reactivex.schedulers.Schedulers
import tk.paulmburu.daggerandroid_practice.models.User
import tk.paulmburu.daggerandroid_practice.network.auth.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {
    val TAG = "AuthViewModel"
    var thisAuthApi = authApi

    var authUser: MediatorLiveData<User> = MediatorLiveData()

    fun authenticateWitId(userId: Int){
        var source: LiveData<User> = LiveDataReactiveStreams.fromPublisher(
            thisAuthApi.getUser(userId)
                .subscribeOn(Schedulers.io())
        )

        authUser.addSource(source, Observer<User>(){
            authUser.value = it
            authUser.removeSource(source)
        })
    }

    fun observeUser(): LiveData<User>{
        return authUser
    }


    init {
        Log.d(TAG,"AuthViewModel: viewmodel is working")
        getUser()
    }

    fun getUser(){
        Log.d(TAG,"AuthViewModel: get user")
        thisAuthApi.getUser(1)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe{
                Log.d(TAG,"onNext =  ${it.email}")
            }
    }

    fun checkAuthApiNull(){
        if (thisAuthApi == null){
            Log.d(TAG,"AuthViewModel: auth api is NULL")
        }else{
            Log.d(TAG,"AuthViewModel: auth api is NOT NULL")
        }
    }
}