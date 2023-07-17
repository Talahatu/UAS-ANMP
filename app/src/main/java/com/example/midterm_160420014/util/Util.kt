package com.example.midterm_160420014.util

import android.content.Context
import androidx.navigation.NavController
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

val MIGRATION_3_4 = object :Migration(3,4){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE histories (" +
                    "'quantity' INT NOT NULL," +
                    "'notes' TEXT NOT NULL," +
                    "'user_id' INT NOT NULL," +
                    "'menu_id' INT NOT NULL," +
                    "'date' TEXT NOT NULL," +
                    "'uuid' INT NOT NULL," +
                    "PRIMARY KEY('uuid'))"
        )
    }
}
val MIGRATION_4_5 = object :Migration(4,5){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE histories DROP COLUMN notes;" +
                    "ALTER TABLE histories ADD subtotal INT NOT NULL;"
        )
    }
}
val MIGRATION_5_6 = object :Migration(5,6){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE users ADD saldo INT DEFAULT 0;" +
                    "ALTER TABLE users ADD alamat TEXT DEFAULT NULL;"
        )
    }
}
fun buildDB(context: Context): KulinerDatabase{
 return Room.databaseBuilder(
     context,
     KulinerDatabase::class.java,
     DB_NAME).addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5,
     MIGRATION_5_6).build();
}
