package org.ak.nfn.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.ResponseBody
import org.ak.nfn.data.network.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
val TAG = "HELLO"
class UserRepository {

    fun userLogin(email: String, password: String): LiveData<String>{
        val loginResponse = MutableLiveData<String>()

        MyApi().userLogin(email, password)
            .enqueue(object: Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful){
                        loginResponse.value = response.body()?.string()
                        Log.d(TAG, "onResponse: success ${loginResponse.toString()}")
                    }else{
                        Log.d(TAG, "onResponse: failed ${response.body()?.string()}")
                        if (response.body()?.string().isNullOrEmpty()){
                            loginResponse.value = "No Response"
                            return
                        }
                        loginResponse.value = response.body()?.string()
                    }
                }

            })

        return loginResponse
    }

}