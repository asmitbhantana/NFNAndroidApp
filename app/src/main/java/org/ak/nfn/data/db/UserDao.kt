package org.ak.nfn.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.ak.nfn.data.db.entities.CURRENT_USER_ID
import org.ak.nfn.data.db.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User): Long

    @Query("Select * from User where uid = $CURRENT_USER_ID")
    fun getUser(): LiveData<User>
}