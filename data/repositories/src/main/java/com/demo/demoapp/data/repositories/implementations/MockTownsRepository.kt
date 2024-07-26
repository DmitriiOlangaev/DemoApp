package com.demo.demoapp.data.repositories.implementations

import com.demo.demoapp.data.network.NetworkDataSource
import com.demo.demoapp.data.repositories.interfaces.TownsRepository
import com.demo.demoapp.data.repositories.suspendRunCatching
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

internal class MockTownsRepository @Inject constructor(private val networkDataSource: NetworkDataSource) :
    TownsRepository {
    override fun getTowns(): Flow<List<String>> = flow {
        emit(suspendRunCatching { networkDataSource.getTowns() }.let {
            if (it.isSuccess) {
                it.getOrThrow()
            } else {
                mutableSetOf<String>().apply {
                    val allowed = 'а'..'я'
                    for(c1 in allowed) {
                        for(c2 in allowed) {
                            repeat(10) {
                                val length = Random.nextInt(3, 10)
                                add("${c1.uppercaseChar()}$c2${(1..length).map { allowed.random() }.joinToString("")}")
                            }
                        }
                    }
                }.toList()
            }
        }.map { it.trim() })
    }
}