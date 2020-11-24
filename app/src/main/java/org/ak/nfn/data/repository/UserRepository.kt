package org.ak.nfn.data.repository

import org.ak.nfn.data.db.AppDatabase
import org.ak.nfn.data.db.entities.UserToken
import org.ak.nfn.data.network.MyApi
import org.ak.nfn.data.network.SafeApiRequest
import org.ak.nfn.data.network.responses.AuthResponse
import org.ak.nfn.data.network.responses.UserSignUpResponse
import retrofit2.Response

val TAG = "HELLO"
class UserRepository(
    private val api:MyApi,
    private val appDatabase: AppDatabase
) : SafeApiRequest(){

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest {
            api.userLogin(email,password)
        }
    }

    suspend fun userSignUp(email: String,
                       password: String,
                       first_name: String,
                       last_name: String,
                       gender: Int,
                       citizenship_number: String,
                       current_address: String,
                       permanent_address: String,
                       username: String)
            : UserSignUpResponse{
        return apiRequest { api.userSignUp(email, password, first_name, last_name, gender, citizenship_number, current_address, permanent_address,username)
        }
    }

    suspend fun saveToken(userToken: UserToken) = appDatabase.getUserDao().upsert(userToken)



    fun getUser() = appDatabase.getUserDao().getUser()

}