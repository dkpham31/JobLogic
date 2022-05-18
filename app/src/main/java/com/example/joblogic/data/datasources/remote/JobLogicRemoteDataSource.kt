package com.example.joblogic.data.datasources.remote

import com.example.joblogic.data.datasources.model.BuyListResult
import com.example.joblogic.data.datasources.model.ContactListResult
import retrofit2.http.GET

interface JobLogicRemoteDataSource {
    // From API call list database
    @GET("imkhan334/demo-1/call")
    suspend fun getContactList(): ContactListResult

    // From API buy list database
    @GET("imkhan334/demo-1/buy")
    suspend fun getBuyList(): BuyListResult
}