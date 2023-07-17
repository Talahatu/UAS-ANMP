package com.example.midterm_160420014.view

import android.view.View
import com.example.midterm_160420014.model.Users

interface ProfileOnClickListener{
    fun onProfileClick(v:View)
}
interface EditProfileListener{
    fun onEditProfileClick(v:View, user:Users)
}