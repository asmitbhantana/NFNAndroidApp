package org.ak.nfn.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity
class UserToken(
    //getting response from the api is stored on this database
    var token:String ? = null
) {
    //in case of conflict
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}