package com.example.midterm_160420014.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("SELECT * FROM histories WHERE user_id=:uuid")
    fun selectAll(uuid:Int):List<History>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg history:History)
}