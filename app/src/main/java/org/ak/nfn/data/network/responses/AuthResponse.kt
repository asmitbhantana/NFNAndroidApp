package org.ak.nfn.data.network.responses

import org.ak.nfn.data.db.entities.UserToken

data class AuthResponse(
    val token: UserToken? = null
)