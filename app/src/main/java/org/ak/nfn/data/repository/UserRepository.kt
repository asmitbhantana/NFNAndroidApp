package org.ak.nfn.data.repository

import org.ak.nfn.data.network.MyApi
import org.ak.nfn.data.network.responses.AuthResponse
import retrofit2.Response

val TAG = "HELLO"
class UserRepository {

    suspend fun userLogin(email: String, password: String): Response<AuthResponse> {
        return MyApi().userLogin(email,password)
    }

}