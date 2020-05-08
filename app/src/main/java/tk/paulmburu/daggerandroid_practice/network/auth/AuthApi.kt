package tk.paulmburu.daggerandroid_practice.network.auth

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import tk.paulmburu.daggerandroid_practice.models.User


interface AuthApi {
    @GET("users/{id}")
    fun getUser(@Path("id") id : Int): Flowable<User>
}