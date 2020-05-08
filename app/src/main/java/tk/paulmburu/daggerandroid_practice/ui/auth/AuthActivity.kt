package tk.paulmburu.daggerandroid_practice.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import tk.paulmburu.daggerandroid_practice.R
import tk.paulmburu.daggerandroid_practice.models.User
import tk.paulmburu.daggerandroid_practice.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    lateinit var viewModel: AuthViewModel

    lateinit var userId : EditText

    val TAG = "AuthActivity"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        userId = findViewById(R.id.user_id_input)

        findViewById<Button>(R.id.login_button).setOnClickListener(this)

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)
        setLogo()
        subscribeObservers()
    }

    fun subscribeObservers(){
        viewModel.observeUser().observe(this, Observer<User>(){
            if (it != null){
                Log.d(TAG, "onChanged ${it.email}")
            }
        })
    }

    fun setLogo(){
        requestManager
            .load(logo)
            .into(findViewById(R.id.login_logo) as ImageView)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.login_button -> {
                    attemptLogin()
                    return
                }
            }
        }
    }

    private fun attemptLogin() {
        if(TextUtils.isEmpty(userId.text.toString())){
            return
        }
        viewModel.authenticateWitId( (userId.text.toString()).toInt()   )
    }
}
