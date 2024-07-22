package com.demo.demoapp.domain.usecases

import coil.ImageLoader
import coil.request.ImageRequest
import com.demo.demoapp.data.repositories.interfaces.ConcertsRepository
import com.demo.demoapp.domain.model.Concert
import com.demo.demoapp.domain.model.Price
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetConcertsUseCase @Inject constructor(
    private val concertsRepository: ConcertsRepository,
    private val imageLoader: ImageLoader,
    private val imageRequestBuilder: ImageRequest.Builder
) {
    operator fun invoke(): Flow<List<Concert>> = concertsRepository.getConcerts().map { list ->
        list.map {
            val imageRequest =
                imageRequestBuilder.data("https://random.danielpetrica.com/api/random").build()
            Concert(
                id = it.id,
                drawable = imageLoader.execute(imageRequest).drawable!!,
                title = it.title,
                town = it.town,
                price = Price(it.price)
            )
        }
    }
}