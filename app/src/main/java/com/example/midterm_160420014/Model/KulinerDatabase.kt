package com.example.midterm_160420014.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Users::class), version = 1)
abstract class KulinerDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object{
        @Volatile private var instance:KulinerDatabase ?= null
        private val LOCK = Any()
        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                KulinerDatabase::class.java,
                "kulinerDB"
            ).build()
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