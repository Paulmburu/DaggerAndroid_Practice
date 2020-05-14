package tk.paulmburu.daggerandroid_practice.ui.main.posts

import android.util.Log
import androidx.lifecycle.*
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import tk.paulmburu.daggerandroid_practice.SessionManager
import tk.paulmburu.daggerandroid_practice.models.Post
import tk.paulmburu.daggerandroid_practice.network.main.MainApi
import tk.paulmburu.daggerandroid_practice.ui.main.Resource
import javax.inject.Inject


class PostsViewModel @Inject constructor(sessionManager: SessionManager, mainApi: MainApi): ViewModel() {
    val TAG = "PostsViewModel"

    val sessionManager = sessionManager
    val mainApi = mainApi

    var posts: MediatorLiveData<Resource<List<Post>>> = MediatorLiveData()

    init {
        Log.d(TAG,"PostViewModel: ViewModel is working...")
    }


    fun observePosts(): LiveData<Resource<List<Post>>> {
        if (posts.value == null) {
            posts.setValue(Resource.loading(null))
            val source: LiveData<Resource<List<Post>>> =
                LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPostsFromUser(sessionManager.getAuthUser().value!!.data!!.id)
                        .onErrorReturn(
                            Function<Throwable, List<Post>> {
                                Log.e(TAG, "apply: ", it)
                                val post = Post()
                                post.id = -1
                                val posts: ArrayList<Post> = ArrayList()
                                posts.add(post)
                                return@Function posts
                        })
                        .map(
                            Function<List<Post>, Resource<List<Post>>>() {
                                if (it.size > 0) {
                                    if (it[0].id == -1) {
                                        return@Function Resource.error("Something went wrong", null)
                                    }
                                }
                                return@Function Resource.success(it)
                        })
                        .subscribeOn(Schedulers.io())
                )
            posts.addSource(
                source, Observer<Resource<List<Post>>>() {
                        posts.setValue(it)
                        posts.removeSource(source)
                })
        }
        return posts
    }
}