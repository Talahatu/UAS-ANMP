package com.example.midterm_160420014.model

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun selectAll():List<Users>

    @Query("SELECT * FROM users WHERE uuid=:id")
    fun selectById(id:Int):Users

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: Users)

    @Query("SELECT * FROM users WHERE email=:email AND password=:password")
    fun login(email:String,password:String):Users

    @Query("UPDATE users SET saldo=saldo+:nominal WHERE uuid=:uuid")
    fun updateSaldo(nominal:Int, uuid:Int):Int

    @Query("UPDATE users SET saldo=saldo-:nominal WHERE uuid=:uuid")
    fun deductSaldo(nominal: Int,uuid: Int):Int

    @Query("UPDATE users SET name=:name, email=:email, password=:password, alamat=:alamat WHERE uuid=:uuid")
    fun updateProfile(name:String,email:String,password:String,alamat:String,uuid:Int):Int

    @Delete
    fun deactivateAccount(user:Users)
}