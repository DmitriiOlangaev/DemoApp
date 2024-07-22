package com.demo.demoapp.domain.usecases

import com.demo.demoapp.data.repositories.interfaces.TownsRepository
import javax.inject.Inject

class GetTownsUseCase @Inject constructor(private val townsRepository: TownsRepository) {
    operator fun invoke() = townsRepository.getTowns()
}