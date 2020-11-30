package org.ak.nfn.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class User(
        var email:String ?=null,
        var gender: String? = null,
        var citizenship_number: String?= null,
        var current_address: String?= null,
        var permanent_address: String?= null,
        var username: String?= null,
        var date_joined: String?= null,
        var pk: Int?= null
){
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}