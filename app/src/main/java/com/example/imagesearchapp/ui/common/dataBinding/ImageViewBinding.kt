package com.example.imagesearchapp.ui.common.dataBinding

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.imagesearchapp.R

@BindingAdapter(value = ["loadImage", "progressBar"])
fun loadImage(imageView: ImageView, imageUrl: String?, progressBar: ProgressBar?) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerInside()
        .apply(
            RequestOptions
                .skipMemoryCacheOf(false)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
        )
        .thumbnail(0.1f)
        .error(R.drawable.ic_error)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar?.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar?.visibility = View.GONE
                return false
            }
        })
        .into(imageView)
}

@BindingAdapter("loadRoundedImage")
fun loadRoundedImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(
            RequestOptions
                .bitmapTransform(RoundedCorners(12))
        )
        .thumbnail(0.1f)
        .error(R.drawable.ic_error)
        .into(imageView)
}