package com.demo.demoapp.data.network.retrofit

import com.demo.demoapp.data.model.Concert
import com.demo.demoapp.data.model.ConcertsResponse
import com.demo.demoapp.data.model.Ticket
import com.demo.demoapp.data.model.TicketOffer
import com.demo.demoapp.data.network.NetworkDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Lazy
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import java.util.Date
import javax.inject.Inject


private const val BASE_URL = "https://run.mocky.io/v3/"

private interface RetrofitNetworkApi {
    @GET("acccc639-ff70-4d54-a913-0a856107dd25")
    suspend fun getConcerts(): ConcertsResponse

    @GET("5b4414ac-4a08-47d8-be8e-17f15f78cc85")
    suspend fun getTowns(): List<String>

    @GET
    suspend fun getTicketsOffers(from: String, to: String): List<TicketOffer>

    @GET
    suspend fun getTickets(
        from: String,
        to: String,
        date: Date,
        backDate: Date? = null
    ): List<Ticket>

}

internal class RetrofitNetwork @Inject constructor(
    networkJson: Json,
    okHttpCallFactory: Lazy<Call.Factory>
) : NetworkDataSource {

    private val networkApi =
        Retrofit.Builder().baseUrl(BASE_URL).callFactory { okHttpCallFactory.get().newCall(it) }
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build().create(RetrofitNetworkApi::class.java)

    override suspend fun getConcerts(): List<Concert> = networkApi.getConcerts().offers

    override suspend fun getTowns(): List<String> =
        networkApi.getTowns()

    override suspend fun getTicketsOffers(from: String, to: String): List<TicketOffer> =
        networkApi.getTicketsOffers(from = from, to = to)

    override suspend fun getTickets(
        from: String,
        to: String,
        date: Date,
        backDate: Date?
    ): List<Ticket> =
        networkApi.getTickets(from = from, to = to, date = date, backDate = backDate)
}