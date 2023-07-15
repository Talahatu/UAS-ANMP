package com.example.midterm_160420014.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.midterm_160420014.util.MIGRATION_1_2

@Database(entities = arrayOf(Users::class,Menus::class), version = 2)
abstract class KulinerDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun menuDao(): MenuDao
    companion object{
        @Volatile private var instance:KulinerDatabase ?= null
        private val LOCK = Any()
        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                KulinerDatabase::class.java,
                "kulinerDB"
            ).addMigrations(MIGRATION_1_2).build()
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