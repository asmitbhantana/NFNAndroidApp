package org.ak.nfn.ui.auth

interface AuthListener {
    fun onStarted()
    fun onFailure(message: String)
    fun onSuccess()
}