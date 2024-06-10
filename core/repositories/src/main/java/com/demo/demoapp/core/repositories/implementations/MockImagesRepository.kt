package com.demo.demoapp.core.repositories.implementations

import android.app.Application
import android.graphics.drawable.Drawable
import com.demo.demoapp.core.common.di.AppDispatcher
import com.demo.demoapp.core.common.di.Dispatcher
import com.demo.demoapp.core.repositories.R
import com.demo.demoapp.core.repositories.interfaces.ImagesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

internal class MockImagesRepository @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val application: Application
) : ImagesRepository {
    private val delays: List<Long> = listOf(500, 100, 1500)
    private val random = Random(1000 - 7)
    override suspend fun getImage(id: Int): Drawable =
        withContext(ioDispatcher) {
            delay(delays[random.nextInt(0, 3)])
            when (id) {
                1 -> application.resources.getDrawable(R.drawable.one, null)
                2 -> application.resources.getDrawable(R.drawable.two, null)
                3 -> application.resources.getDrawable(R.drawable.three, null)
                else -> throw IllegalArgumentException("Illegal image id")
            }
        }
}