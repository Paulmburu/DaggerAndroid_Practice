package tk.paulmburu.daggerandroid_practice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity
import tk.paulmburu.daggerandroid_practice.ui.auth.AuthActivity
import tk.paulmburu.daggerandroid_practice.ui.auth.AuthResource
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity() {

    open val TAG = "BaseActivity"

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    fun subscribeObservers(){
        sessionManager.getAuthUser().observe(this, Observer {
            if (it != null){
                when(it.status){
                    AuthResource.AuthStatus.LOADING -> {
                        return@Observer
                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        Log.d(TAG, "onChange : LOGIN SUCCESS: " + it.data?.email)
                        return@Observer
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        Log.d(TAG, "onChanged: " + it.message)
                        return@Observer
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        navLoginScreen()
                        return@Observer
                    }
                }
            }
        })
    }

    fun navLoginScreen(){
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}