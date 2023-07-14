package com.example.midterm_160420014.Model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("id")
    val id:String?,
    @SerializedName("name")
    val name:String?,
    @SerializedName("email")
    val email:String?,
    @SerializedName("password")
    val password:String?
)

data class ApiResponse(
    @SerializedName("status")
    val status:String?,
    @SerializedName("message")
    val message:String?,
)

data class Restaurant(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("address")
    val address:String,
    @SerializedName("link")
    val link:String,
    @SerializedName("phone")
    val phone:String
)

data class Menu(
    @SerializedName("id")
    val id:Int,
    @SerializedName("restaurant_id")
    val restoId:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("price")
    val price:Int,
    @SerializedName("link")
    val link:String,
)

data class Review(
    @SerializedName("id")
    val id:Int,
    @SerializedName("resto_id")
    val restoId:Int,
    @SerializedName("user_id")
    val userId:Int,
    @SerializedName("comment")
    val content:String
)

data class History(
    @SerializedName("id")
    val id:Int,
    @SerializedName("quantity")
    val qty:Int,
    @SerializedName("notes")
    val notes:String,
    @SerializedName("user_id")
    val user_id:Int,
    @SerializedName("menu_id")
    val menu_id:Int,
    @SerializedName("date")
    val date:String
)
data class Promo(
    @SerializedName("id")
    val id:Int,
    @SerializedName("code")
    val code:String,
    @SerializedName("discount")
    val discount:Int,
    @SerializedName("restaurant_id")
    val restaurant_id:Int
)
