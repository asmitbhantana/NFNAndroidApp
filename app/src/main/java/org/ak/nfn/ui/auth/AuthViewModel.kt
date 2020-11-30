package org.ak.nfn.ui.auth

import android.accounts.AuthenticatorException
import android.util.Log
import androidx.lifecycle.ViewModel
import org.ak.nfn.data.db.entities.User
import org.ak.nfn.data.db.entities.UserToken
import org.ak.nfn.data.pojo.UserSignup
import org.ak.nfn.data.repository.UserRepository
import org.ak.nfn.utils.ApiException
import org.ak.nfn.utils.Coroutines
import org.ak.nfn.utils.NoInternetException

private val TAG = "AuthViewModel"
class AuthViewModel(
    private val userRepository: UserRepository
):ViewModel() {
    //for login
    var email: String?=null
    var password: String?=null

    var authListener:AuthListener? = null

    //for signup
    var userSignup: UserSignup = UserSignup()

    var signupEmail: String? = null
    var signupPhone: String? = null
    var signupPassword: String? = null
    var signupConfirmPassword: String? = null

    var signupFirstName: String? = null
    var signupLastName: String? = null
    var signupDob: String? = null
    var signupGender: String? = null
    var signupCitizenship: String? = null
    var signupCurrentAddress: String? = null
    var signupPermanentAddress : String? = null
//    var signupAuthListener: AuthListener? = null

    fun getLoggedInUserToken() = userRepository.getUserToken()
    fun getSignedUpUser() = userRepository.getUser()

    fun onLoginButtonClicked(){
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty())
        {
            authListener?.onFailure("Invalid email or password")
            return
        }else{
            //success
            Coroutines.main {
                try {
                    val authResponse = userRepository.userLogin(email!!,password!!)
                    authResponse.token?.let{
                        authListener?.onSuccess(it)
                        //save the token to user
                        val token = UserToken(token = it)
                        userRepository.saveToken(token)
                        return@main
                    }
                    authListener?.onFailure(authResponse.token!!)
                    Log.d(TAG, "onLoginButtonClicked: "+ authResponse)
                }
                catch (e:ApiException){
                    authListener?.onFailure(e.message!!)
                }
                catch (e:NoInternetException){
                    authListener?.onFailure(e.message!!)
                }
            }
        }
    }

    fun onRegisterButtonClicked() {
        Log.d(TAG, "onRegisterButtonClicked: email = ${signupEmail}, password = ${signupPassword}")
        if(signupEmail.isNullOrEmpty() or signupPhone.isNullOrEmpty() or signupPassword.isNullOrEmpty() or signupConfirmPassword.isNullOrEmpty()){
            authListener?.onFailure("Please Fill Up All Fields!")
            return
        }
        if(signupPassword!!.length<8){
            authListener?.onFailure("Password Length Must be 8 character long")
            return
        }
        if(signupPassword!! != signupConfirmPassword){
            authListener?.onFailure("Password Doesn't Match!")
            return
        }

        userSignup.email = signupEmail
        userSignup.password = signupPassword
        userSignup.phone_number = signupPhone

        authListener?.onSuccess("none")
    }

    fun onCreateAccountClicked(){

        if (signupFirstName.isNullOrEmpty() or signupLastName.isNullOrEmpty() or
                signupDob.isNullOrEmpty() or signupGender.isNullOrEmpty() or signupCitizenship.isNullOrEmpty()
                or signupCurrentAddress.isNullOrEmpty() or signupPermanentAddress.isNullOrEmpty()){
            authListener?.onFailure("Please fill up all fields.")
            return
        }

        userSignup.first_name = signupFirstName
        userSignup.last_name = signupLastName
        userSignup.username = signupFirstName + "_" + signupLastName
        userSignup.dob = signupDob
        userSignup.gender = signupGender
        userSignup.citizenship_number = signupCitizenship
        userSignup.current_address = signupCurrentAddress
        userSignup.permanent_address = signupPermanentAddress

        authListener?.onStarted()

        Coroutines.main {
            try{
                val signupResponse =
                        userRepository.userSignUp(userSignup)
                signupResponse.let {
                    authListener?.onStarted()
                    val user = User(it.email,it.gender,it.citizenship_number,
                            it.current_address,it.permanent_address,it.username,it.date_joined, it.pk)
                    userRepository.saveUser(user)
                    authListener?.onSuccess("")
                    return@main
                }
                authListener?.onFailure("Error occured on Signup!")
            }
            catch (e:ApiException){
                authListener?.onFailure(e.message!!)
            }
            catch(e:AuthenticatorException){
                authListener?.onFailure(e.message!!)
            }catch (e:NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }


    }
}