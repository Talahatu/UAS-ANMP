package com.example.midterm_160420014.Model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun selectAll():List<Users>
}