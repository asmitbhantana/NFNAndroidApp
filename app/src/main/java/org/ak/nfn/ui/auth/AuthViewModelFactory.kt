package org.ak.nfn.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.ak.nfn.data.repository.UserRepository

class AuthViewModelFactory(
    private val userRepository: UserRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(userRepository) as T
    }

}