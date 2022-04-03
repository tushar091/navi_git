package com.example.navi_git.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindingUtils {
    companion object{
        @JvmStatic
        @BindingAdapter(
            "srcUrl",
            "circleCrop",
            "placeholder",
            requireAll = false // make the attributes optional
        )
        fun ImageView.bindSrcUrl(
            url: String?,
            circleCrop: Boolean = false,
            placeholder: Drawable?,
        ) = Glide.with(this).load(url).let { request ->
            if (circleCrop) {
                request.circleCrop()
            }
            if (placeholder != null) {
                request.placeholder(placeholder)
            }
            request.into(this)
        }
    }
}