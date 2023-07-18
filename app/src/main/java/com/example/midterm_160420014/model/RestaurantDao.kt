package com.example.midterm_160420014.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurants")
    fun selectAll():List<Restaurants>

    @Query("SELECT * FROM restaurants WHERE uuid=:id")
    fun selectById(id:Int):Restaurants

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg restaurants: Restaurants)
}