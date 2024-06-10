package com.demo.demoapp.core.repositories.interfaces

import android.graphics.drawable.Drawable


interface ImagesRepository {
    suspend fun getImage(id: Int): Drawable
}