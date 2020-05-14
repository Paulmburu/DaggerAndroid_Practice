package tk.paulmburu.daggerandroid_practice.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import tk.paulmburu.daggerandroid_practice.R
import tk.paulmburu.daggerandroid_practice.models.Post
import tk.paulmburu.daggerandroid_practice.ui.main.Resource
import tk.paulmburu.daggerandroid_practice.util.VerticalSpacingItemDecoration
import tk.paulmburu.daggerandroid_practice.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class PostsFragment : DaggerFragment() {
    val TAG = "PostsFragment"

    lateinit var viewModel: PostsViewModel
    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var postsAdapter: PostsRecyclerAdapter

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        viewModel = ViewModelProviders.of(this,providerFactory).get(PostsViewModel::class.java)
        initRecyclerView()
        subscibeObservers()
    }

    fun subscibeObservers(){
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner, Observer<Resource<List<Post>>> {
            if(it != null){
                when(it.status){
                    Resource.Status.LOADING -> {
                        Log.d(TAG,"onChanged: LOADING...")
                        return@Observer
                    }
                    Resource.Status.SUCCESS -> {
                        Log.d(TAG,"onChanged: got posts...")
                        postsAdapter.setPosts(it.data!!)
                        return@Observer
                    }
                    Resource.Status.ERROR -> {
                        Log.d(TAG,"onChanged: ERROR ${it.message}")
                    }
                }
            }
        })
    }

    fun initRecyclerView(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            var itemDecoration: VerticalSpacingItemDecoration = VerticalSpacingItemDecoration(15)

            addItemDecoration(itemDecoration)
            adapter = postsAdapter
        }
    }
}