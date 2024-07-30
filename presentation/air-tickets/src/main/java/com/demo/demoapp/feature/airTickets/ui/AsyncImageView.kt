package com.demo.demoapp.feature.airTickets.ui

import android.content.Context
import android.widget.ImageView
import coil.ImageLoader
import coil.request.ImageRequest
import com.demo.demoapp.core.common.setVisible
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal data class AsyncImageView @AssistedInject constructor(
    @Assisted private val imageView: ImageView,
    @Assisted private val shimmerContainer: ShimmerFrameLayout,
    private val context : Context,
    private val imageLoader : ImageLoader
) {
    fun downloadImage(id: Int) {
        imageView.setVisible(false)
        shimmerContainer.showShimmer(true)
        ImageRequest.Builder(context)
        val request = ImageRequest.Builder(context).data("https://random.danielpetrica.com/api/random").target(imageView).listener { request, result ->
            shimmerContainer.hideShimmer()
            imageView.setVisible(true)
        }.build()
    }

    @AssistedFactory
    internal interface Factory {
        fun create(imageView: ImageView, shimmerContainer: ShimmerFrameLayout): AsyncImageView
    }
}