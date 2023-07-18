package com.example.midterm_160420014.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.midterm_160420014.util.*

@Database(entities = arrayOf(Users::class, Menus::class, Restaurants::class,History::class, Review::class), version = 7)
abstract class KulinerDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun menuDao(): MenuDao
    abstract fun historyDao():HistoryDao
    abstract fun restaurantDao(): RestaurantDao
    abstract  fun ReviewDao(): ReviewDao
    companion object{
        @Volatile private var instance:KulinerDatabase ?= null
        private val LOCK = Any()
        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                KulinerDatabase::class.java,
                "kulinerDB"
            ).addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5,MIGRATION_5_6,MIGRATION_6_7).build()
        operator fun invoke(context: Context){
            if(instance!=null){
                synchronized(LOCK){
                    instance?: buildDatabase(context).also {
                        instance=it
                    }
                }
            }
        }
    }
}