package org.ak.nfn.data.network

import okhttp3.OkHttpClient
import org.ak.nfn.data.network.responses.AuthResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("login/")
    suspend fun userLogin(
        @Field("email")email: String,
        @Field("password")password: String
    ): Response<AuthResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi{

            val okHttpClient  = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://nfnapi.herokuapp.com/api/user/")
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
                .build()
                .create(MyApi::class.java)
        }
    }

}