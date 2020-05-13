package tk.paulmburu.daggerandroid_practice.network.main

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import tk.paulmburu.daggerandroid_practice.models.Post

interface MainApi {

    // /posts?userId=1
    @GET("posts")
    fun getPostsFromUser(
        @Query("userId") id: Int
    ): Flowable<List<Post>>
}