package com.demo.demoapp.domain.usecases

import com.demo.demoapp.data.datastore.PreferencesDataSource
import com.demo.demoapp.data.repositories.interfaces.TownsRepository
import com.demo.demoapp.domain.model.DestinationSuggestion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetToDestinationsUseCase @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
    private val townsRepository: TownsRepository
) {
    operator fun invoke(): Flow<List<DestinationSuggestion>> =
        combine(getToFlow(), getSuggestionsFlow()) { toList, suggestionsList ->

            toList.toMutableList().apply {
                removeAll { toDest ->
                    suggestionsList.any { destinationSuggestion ->  destinationSuggestion.town == toDest.town}
                }
            } + suggestionsList
        }

    private fun getToFlow(): Flow<List<DestinationSuggestion>> =
        preferencesDataSource.data.map { it.enteredTo }
            .map { list -> list.map { DestinationSuggestion(it, "Вы искали раньше") } }

    private fun getSuggestionsFlow(): Flow<List<DestinationSuggestion>> = townsRepository.getTowns()
        .map { list ->
            list.subList(0, 10).map { DestinationSuggestion(it, "Популярное направление") }
        }
}