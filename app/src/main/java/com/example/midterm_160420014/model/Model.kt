package com.example.midterm_160420014.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class Users(
    @ColumnInfo("name")
    var name:String?,
    @ColumnInfo("email")
    var email:String?,
    @ColumnInfo("password")
    var password:String?,
    @ColumnInfo("saldo")
    var saldo:Int?,
    @ColumnInfo("alamat")
    var alamat:String?

) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
@Entity()
data class Restaurants(
    @ColumnInfo("name")
    val name:String,
    @ColumnInfo("address")
    val address:String,
    @ColumnInfo("phone")
    val phone:String
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}

@Entity()
data class Menus(
    @ColumnInfo("restaurant_id")
    val restoId:Int,
    @ColumnInfo("name")
    val name:String,
    @ColumnInfo("description")
    val description:String,
    @ColumnInfo("price")
    val price:Int,
    @ColumnInfo("link")
    val link:String,
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}

@Entity("reviews")
data class Review(
    @ColumnInfo("resto_id")
    val restoId:Int,
    @ColumnInfo("menu_id")
    val menuId:Int,
    @ColumnInfo("user_id")
    val userId:Int,
    @ColumnInfo("comment")
    val content:String
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}


@Entity("histories")
data class History(
    @ColumnInfo("quantity")
    val qty:Int,
    @ColumnInfo("subtotal")
    val subtotal:Int,
    @ColumnInfo("user_id")
    val user_id:Int,
    @ColumnInfo("menu_id")
    val menu_id:Int,
    @ColumnInfo("date")
    val date:String,
    @ColumnInfo("alamat_tujuan")
    val alamat:String
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}


@Entity()
data class Promos(
    @ColumnInfo("id")
    val id:Int,
    @ColumnInfo("code")
    val code:String,
    @ColumnInfo("discount")
    val discount:Int,
    @ColumnInfo("restaurant_id")
    val restaurant_id:Int
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}
