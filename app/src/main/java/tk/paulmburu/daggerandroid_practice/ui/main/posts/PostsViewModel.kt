package tk.paulmburu.daggerandroid_practice.ui.main.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import tk.paulmburu.daggerandroid_practice.SessionManager
import tk.paulmburu.daggerandroid_practice.network.main.MainApi
import javax.inject.Inject

class PostsViewModel @Inject constructor(sessionManager: SessionManager, mainApi: MainApi): ViewModel() {
    val TAG = "PostsViewModel"

    init {
        Log.d(TAG,"PostViewModel: ViewModel is working...")
    }
}