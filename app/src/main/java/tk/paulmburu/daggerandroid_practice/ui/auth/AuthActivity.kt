package tk.paulmburu.daggerandroid_practice.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import tk.paulmburu.daggerandroid_practice.R
import tk.paulmburu.daggerandroid_practice.models.User
import tk.paulmburu.daggerandroid_practice.ui.main.MainActivity
import tk.paulmburu.daggerandroid_practice.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    lateinit var viewModel: AuthViewModel

    lateinit var userId : EditText

    lateinit var progressBar: ProgressBar

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
        progressBar = findViewById(R.id.progress_bar)

        findViewById<Button>(R.id.login_button).setOnClickListener(this)

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)
        setLogo()
        subscribeObservers()
    }

    fun subscribeObservers(){
        viewModel.observeAuthState().observe(this, Observer<AuthResource<User>>(){
            if (it != null){
                when(it.status){
                    AuthResource.AuthStatus.LOADING -> {
                        showProgressBar(true)
                        return@Observer
                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        Log.d(TAG, "onChange: LOGIN SUCCESS: ${it.data?.email}")
                        onLoginSuccess()
                        return@Observer
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        showProgressBar(false)
                         Toast.makeText(this, "${it.message} \n Did you a number between 1 and 10?", Toast.LENGTH_LONG).show()
                        return@Observer
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        showProgressBar(false)
                        return@Observer
                    }
                }
            }
        })
    }

    fun onLoginSuccess(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun showProgressBar(isVisible: Boolean){
        if(isVisible){
            progressBar.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.GONE
        }

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
