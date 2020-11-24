package org.ak.nfn.ui.auth

import org.ak.nfn.data.db.entities.UserToken

interface AuthListener {
    fun onStarted()
    fun onFailure(message: String)
    fun onSuccess(userToken: String)
}