package tk.paulmburu.daggerandroid_practice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import tk.paulmburu.daggerandroid_practice.models.User
import tk.paulmburu.daggerandroid_practice.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    val TAG: String = "SessionManager"

    var cachedUser: MediatorLiveData<AuthResource<User>>  = MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthResource<User>>){
        if(cachedUser != null){
            cachedUser.value = AuthResource.loading(User(0,"","",""))
            cachedUser.addSource(source, Observer{userAuthResource ->
                cachedUser.value =  userAuthResource
                cachedUser.removeSource(source)
            })
        }
    }

    fun logout(){
        Log.d(TAG,"logOut: logging out")
        cachedUser.value =  AuthResource.logout<User>()
    }

    fun getAuthUser(): LiveData<AuthResource<User>>{
        return cachedUser
    }
}