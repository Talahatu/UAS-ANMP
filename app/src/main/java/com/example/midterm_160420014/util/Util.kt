package com.example.midterm_160420014.util

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.midterm_160420014.model.KulinerDatabase

const val DB_NAME = "kulinerDB"

val MIGRATION_1_2 = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE menus " +
                    "('restaurant_id' INTEGER NOT NULL, " +
                    "'name' TEXT NOT NULL, " +
                    "'description' TEXT NOT NULL, " +
                    "'price' INTEGER NOT NULL, " +
                    "'link' TEXT NOT NULL," +
                    "'uuid' INTEGER NOT NULL," +
                    "PRIMARY KEY('uuid'))")
    }
}
val MIGRATION_2_3 = object : Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE restaurants " +
                    "( " +
                    "'name' TEXT NOT NULL, " +
                    "'address' TEXT NOT NULL, " +
                    "'phone' TEXT NOT NULL,'uuid' INTEGER NOT NULL, PRIMARY KEY('uuid'))")
    }
}
fun buildDB(context: Context): KulinerDatabase{
 return Room.databaseBuilder(
     context,
     KulinerDatabase::class.java,
     DB_NAME).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build();
}
