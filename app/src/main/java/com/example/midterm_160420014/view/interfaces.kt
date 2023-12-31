package com.example.midterm_160420014.view

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.midterm_160420014.model.*
import com.squareup.picasso.Picasso

interface ProfileOnClickListener{
    fun onProfileClick(v:View)

    fun onDeactivateClick(v:View,user:Users)
}
interface EditProfileListener{
    fun onEditProfileClick(v:View, user:Users)
}
interface TopUpListener{
    fun onClickTopup(v:View)
}
interface CheckoutListener{
    fun onClickCheckout(v:View,user:Users)

    fun onQtyTextChange(s:CharSequence,start:Int,before:Int,count:Int)
}


interface LogoutListener{
    fun onClickLogout(v:View)
}

interface MenuRestaurantItemLayout{
    fun onDetailClick(v: View, menu: Menus)
}

interface BuyListener{
    fun onBuyDetailClick(v: View, menu: Menus, restaurant: Restaurants)
}

interface ReviewListener{
    fun onReviewDetailClick(v: View, menu: Menus, restaurant: Restaurants)
}
interface AddReviewListener{
    fun onAddReviewClick(v: View)
}

@BindingAdapter("imageUrl")
fun loadImg(view: ImageView, url: String?) {
    if(url!=null){
        Picasso.get().load(url).into(view)
    }
    else{
        Log.d("URL: ","IS NULL")
    }
}