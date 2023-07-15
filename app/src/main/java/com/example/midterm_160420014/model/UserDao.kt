package com.example.midterm_160420014.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun selectAll():List<Users>

    @Query("SELECT * FROM users WHERE uuid=:id")
    fun selectById(id:Int):Users

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: Users)

    @Query("SELECT * FROM users WHERE email=:email AND password=:password")
    fun login(email:String,password:String):Users

}