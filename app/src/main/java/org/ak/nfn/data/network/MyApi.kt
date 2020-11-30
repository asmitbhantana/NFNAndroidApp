package org.ak.nfn.data.network

import okhttp3.OkHttpClient
import org.ak.nfn.data.network.responses.AuthResponse
import org.ak.nfn.data.network.responses.UserSignUpResponse

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
    //email, first_name, last_name, dob, gender,password, citizenship_number, current_address, permanent_address, username
    @FormUrlEncoded
    @POST("signup/")
    suspend fun userSignUp(
        @Field("email")email: String,
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("dob") dob: String,
        @Field("gender")gender: String,
        @Field("password")password: String,
        @Field("citizenship_number")citizenship_number: String,
        @Field("current_address")current_address: String,
        @Field("permanent_address")permanent_address: String,
        @Field("username")username: String
    ): Response<UserSignUpResponse>

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