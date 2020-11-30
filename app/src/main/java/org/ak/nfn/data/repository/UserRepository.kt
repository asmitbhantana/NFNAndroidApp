package org.ak.nfn.data.repository

import org.ak.nfn.data.db.AppDatabase
import org.ak.nfn.data.db.entities.User
import org.ak.nfn.data.db.entities.UserToken
import org.ak.nfn.data.network.MyApi
import org.ak.nfn.data.network.SafeApiRequest
import org.ak.nfn.data.network.responses.AuthResponse
import org.ak.nfn.data.network.responses.UserSignUpResponse
import org.ak.nfn.data.pojo.UserSignup

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

    suspend fun userSignUp(userDetail:UserSignup)
            : UserSignUpResponse{
        return apiRequest {
            api.userSignUp(userDetail.email!!, userDetail.first_name!!, userDetail.last_name!!,
                    userDetail.dob!!, userDetail.gender!!,userDetail.password!!, userDetail.citizenship_number!!,
                    userDetail.current_address!!, userDetail.permanent_address!!, userDetail.username!!)
        }
    }

    suspend fun saveToken(userToken: UserToken) = appDatabase.userTokenDao().upsert(userToken)

    suspend fun saveUser(user: User) = appDatabase.userDao().upsert(user)



    fun getUserToken() = appDatabase.userTokenDao().getUserToken()
    fun getUser() = appDatabase.userDao().getUser()

}