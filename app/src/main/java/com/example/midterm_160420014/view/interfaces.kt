package com.example.midterm_160420014.view

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

}

interface MenuRestaurantItemLayout{
    fun onDetailClick(v: View, menu: Menus)
}

interface BuyListener{
    fun onBuyDetailClick(v: View, menu: Menus, restaurant: Restaurants)
}

interface PromoListener{
    fun onPromoDetailClick(v: View, menu: Menus, promo: Promos)
}
interface ReviewListener{
    fun onReviewDetailClick(v: View, menu: Menus, review: Reviews)
}

@BindingAdapter("imageUrl")
fun loadImg(view: ImageView, url: String) {
    Picasso.get().load(url).into(view)
}