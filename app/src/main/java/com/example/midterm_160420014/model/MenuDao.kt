package com.example.midterm_160420014.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuDao {
    @Query("SELECT * FROM menus")
    fun selectAll():List<Menus>

    @Query("SELECT * FROM menus WHERE uuid=:id")
    fun selectById(id:Int):Menus

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg menus: Menus)
}