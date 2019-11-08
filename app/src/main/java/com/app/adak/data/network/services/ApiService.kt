package com.app.adak.data.network.services


import com.app.adak.data.network.model.UserCreateRes
import com.app.adak.data.network.model.Users
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("users")
    fun getUser(@Query("page") page:Int): Call<Users>


    @POST("users")
    fun createUser(@Body createRes: UserCreateRes): Call<UserCreateRes>

    @PUT("users/{id}")
    fun UpdateUser(@Path("id") id:Int,@Body createRes: UserCreateRes): Call<UserCreateRes>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id:Int): Call<ResponseBody>
}