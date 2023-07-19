package com.example.midterm_160420014.model
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface ReviewDao {
    @Query("SELECT * FROM reviews WHERE resto_id=:resto_id AND menu_id=:menu_id")
    fun selectReviewByMenu(resto_id:Int,menu_id:Int):List<Review>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReview(vararg review: Review)

    @Query("DELETE FROM reviews WHERE user_id=:uuid")
    fun deleteRelatedReview(uuid:Int)
}