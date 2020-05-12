package tk.paulmburu.daggerandroid_practice.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.DaggerFragment
import tk.paulmburu.daggerandroid_practice.R

class ProfileFragment : DaggerFragment() {
    val TAG = "ProfileFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(activity,"Profile Fragment created",Toast.LENGTH_LONG).show()
        return inflater.inflate(R.layout.fragment_profile,container,false)
    }


}