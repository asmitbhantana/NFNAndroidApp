package org.ak.nfn.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.ak.nfn.data.db.entities.User
import org.ak.nfn.data.db.entities.UserToken

@Database(
    entities = [UserToken::class, User::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userTokenDao(): UserTokenDao
    abstract fun userDao(): UserDao

    companion object {
        //immediately visible to other threads
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        //called on the creating of object
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "Mydatabase.db" ).build()
            //video 6

    }
}