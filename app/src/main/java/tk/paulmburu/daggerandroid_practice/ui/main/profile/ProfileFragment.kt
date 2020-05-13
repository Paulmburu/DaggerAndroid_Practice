package tk.paulmburu.daggerandroid_practice.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import tk.paulmburu.daggerandroid_practice.R
import tk.paulmburu.daggerandroid_practice.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {
    val TAG = "ProfileFragment"

    lateinit var viewModel: ProfileViewModel

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

        viewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel::class.java)
    }


}