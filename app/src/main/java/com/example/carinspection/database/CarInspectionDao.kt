package com.example.carinspection.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CarInspectionDao {

    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("select * from users where id=:id")
    fun getUser(id: Long):User

    @Query("select * from users limit 1")
    fun firstUser():User

    @Query("select * from users order by id")
    fun getAllUsers():LiveData<List<User>>
}