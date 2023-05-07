package com.example.taskmanagementapp.constant

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.taskmanagementapp.MainActivity
import com.example.taskmanagementapp.R

@SuppressLint("UnrememberedMutableState")
@Composable
fun loadImage(url : String, activity : MainActivity?) : MutableState<Bitmap?>{
    val imageState : MutableState<Bitmap?> = mutableStateOf(null)
    Glide.with(activity!!).asBitmap().load(R.drawable.avatar).into(object : CustomTarget<Bitmap>(){
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            imageState.value = resource
        }

        override fun onLoadCleared(placeholder: Drawable?) {}
    })
    Glide.with(activity).asBitmap().load(url).into(object : CustomTarget<Bitmap>(){
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            imageState.value = resource
        }

        override fun onLoadCleared(placeholder: Drawable?) {}
    })
    return imageState
}