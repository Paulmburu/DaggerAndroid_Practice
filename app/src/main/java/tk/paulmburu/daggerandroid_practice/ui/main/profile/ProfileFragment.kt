package tk.paulmburu.daggerandroid_practice.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import tk.paulmburu.daggerandroid_practice.R
import tk.paulmburu.daggerandroid_practice.models.User
import tk.paulmburu.daggerandroid_practice.ui.auth.AuthResource
import tk.paulmburu.daggerandroid_practice.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {
    val TAG = "ProfileFragment"

    lateinit var viewModel: ProfileViewModel
    lateinit var email :TextView
    lateinit var username :TextView
    lateinit var website :TextView

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(activity,"Profile Fragment created",Toast.LENGTH_LONG).show()

        return inflater.inflate(R.layout.fragment_profile,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"onViewCreated: ProfileFragment was created ...")

        email = view!!.findViewById(R.id.email)
        username = view!!.findViewById(R.id.username)
        website = view!!.findViewById(R.id.website)

        viewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel::class.java)
        subscribeObservers()
    }

    fun subscribeObservers(){
        viewModel.getAuthenticateUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticateUser().observe(viewLifecycleOwner, Observer<AuthResource<User>>(){
            if(it != null){
                when(it.status){
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        setUserDetails(it.data!!)
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        setErrorDetails(it.message)
                    }
                }
            }
        })
    }

    private fun setUserDetails(data: User) {
        email.setText(data.email)
        username.setText(data.username)
        website.setText(data.website)
    }

    private fun setErrorDetails(message: String?) {
        email.setText(message)
        username.setText("Error")
        website.setText("Error")
    }

}