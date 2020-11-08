package org.ak.nfn.data.db.entities

import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

class User(
    //getting response from the api is stored on this database
    var id: Int? = null,
    var name: String? = null,
    var email: String?= null,
    var password: String?= null,
    var email_verified_at: String?=null,
    var created_at: String ?= null,
    var updated_at: String ?= null
) {
    //in case of conflict
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}