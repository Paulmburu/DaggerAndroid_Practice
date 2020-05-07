package tk.paulmburu.daggerandroid_practice.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import tk.paulmburu.daggerandroid_practice.R
import tk.paulmburu.daggerandroid_practice.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)
        setLogo()

    }

    fun setLogo(){
        requestManager
            .load(logo)
            .into(findViewById(R.id.login_logo) as ImageView)
    }
}
