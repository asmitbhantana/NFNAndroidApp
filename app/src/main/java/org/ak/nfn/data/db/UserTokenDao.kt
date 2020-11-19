package org.ak.nfn.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.ak.nfn.data.db.entities.CURRENT_USER_ID
import org.ak.nfn.data.db.entities.UserToken

@Dao
interface UserTokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(userToken: UserToken) : Long

    @Query("Select * from user where uid = $CURRENT_USER_ID")
    fun getUser(): LiveData<UserToken>

}