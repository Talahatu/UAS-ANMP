package com.example.midterm_160420014.view

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.midterm_160420014.model.*
import com.squareup.picasso.Picasso

interface ProfileOnClickListener{
    fun onProfileClick(v:View)
}
interface EditProfileListener{
    fun onEditProfileClick(v:View, user:Users)
}
interface TopUpListener{
    fun onClickTopup(v:View)
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
    Log.d("DATAS URL: ",url.toString())
    if(url!=null && view!=null){
        Log.d("GAMBAR: ",view.toString())
        Picasso.get().load(url).into(view)
    }
    else{
        Log.d("URL: ","IS NULL")
    }
}