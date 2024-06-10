package com.demo.demoapp.feature.airTickets.ui

import android.widget.ImageView
import android.widget.ProgressBar
import com.demo.demoapp.core.common.di.setVisible
import com.demo.demoapp.core.repositories.interfaces.ImagesRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

internal data class AsyncImageView @AssistedInject constructor(
    @Assisted private val imageView: ImageView,
    @Assisted private val progressBar: ProgressBar,
    private val lifecycleScope: CoroutineScope,
    private val imagesRepository: ImagesRepository,
) {
    private var job: Job? = null
    fun downloadImage(id: Int) {
        job?.cancel()
        imageView.setVisible(false)
        progressBar.setVisible(true)
        job = lifecycleScope.launch {
            ensureActive()
            imageView.setImageDrawable(imagesRepository.getImage(id))
            progressBar.setVisible(false)
            imageView.setVisible(true)
        }

    }

    @AssistedFactory
    internal interface Factory {
        fun create(imageView: ImageView, progressBar: ProgressBar): AsyncImageView
    }
}