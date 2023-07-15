package com.example.midterm_160420014.util

import android.content.Context
import androidx.room.Room
import com.example.midterm_160420014.model.KulinerDatabase

val DB_NAME = "kulinerDB"
fun buildDB(context: Context): KulinerDatabase =
    Room.databaseBuilder(context, KulinerDatabase::class.java, DB_NAME).build();
