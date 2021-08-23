package com.example.contactsdatabase.ProjectDatabase

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user:User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAll() : LiveData<List<User>>
}