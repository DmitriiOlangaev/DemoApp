package com.demo.demoapp.core.network.retrofit

import com.demo.demoapp.core.network.NetworkDataSource
import com.demo.demoapp.core.network.model.Concert
import com.demo.demoapp.core.network.model.Ticket
import com.demo.demoapp.core.network.model.TicketOffer
import com.demo.demoapp.core.network.model.ToDestinationSuggestion
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Lazy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import java.util.Date
import javax.inject.Inject


private const val BASE_URL = "https://run.mocky.io/v3/"

@Serializable
private data class NetworkResponse<T>(
    val data: T,
)

private interface RetrofitNetworkApi {
    @GET("ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getConcerts(): NetworkResponse<List<Concert>>

    @GET
    suspend fun getToDestinationSuggestions(): NetworkResponse<List<ToDestinationSuggestion>>

    @GET("38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getTicketsOffers(from: String, to: String): NetworkResponse<List<TicketOffer>>

    @GET("c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTickets(
        from: String,
        to: String,
        date: Date,
        backDate: Date? = null
    ): NetworkResponse<List<Ticket>>

}

internal class RetrofitNetwork @Inject constructor(
    networkJson: Json,
    okHttpCallFactory: Lazy<Call.Factory>
) : NetworkDataSource {

    private val networkApi =
        Retrofit.Builder().baseUrl(BASE_URL).callFactory { okHttpCallFactory.get().newCall(it) }
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build().create(RetrofitNetworkApi::class.java)

    override suspend fun getConcerts(): List<Concert> = networkApi.getConcerts().data

    override suspend fun getToDestinationSuggestions(): List<ToDestinationSuggestion> =
        networkApi.getToDestinationSuggestions().data

    override suspend fun getTicketsOffers(from: String, to: String): List<TicketOffer> =
        networkApi.getTicketsOffers(from = from, to = to).data

    override suspend fun getTickets(
        from: String,
        to: String,
        date: Date,
        backDate: Date?
    ): List<Ticket> =
        networkApi.getTickets(from = from, to = to, date = date, backDate = backDate).data
}