package com.example.contactsdatabase.ProjectDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getuserDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE : UserDatabase?=null

        fun getInstance(context: Context) : UserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "UserDB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}