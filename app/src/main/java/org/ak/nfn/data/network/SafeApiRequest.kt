package org.ak.nfn.data.network

import android.util.Log
import org.ak.nfn.utils.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.StringBuilder

abstract class SafeApiRequest{
    private val TAG: String?  = "SafeApiRequest"

    suspend fun <T: Any> apiRequest(call: suspend () -> Response<T>): T{
        val response = call.invoke()

        if (response.isSuccessful){
            return response.body()!!
        }
        else{
            val error = response.errorBody()?.string()
            val message = StringBuilder()

            error?.let {
                try {
                    message.append(JSONObject(it))
                }catch (e:JSONException){
                }
                message.append("\n")

            }
//            message.append("Error code: $response")
            Log.d(TAG, "apiRequest: $message")
            throw ApiException(message.toString())
        }
    }

}