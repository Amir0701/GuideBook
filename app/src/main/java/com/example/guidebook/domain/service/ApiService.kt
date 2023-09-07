package com.example.guidebook.domain.service

import com.example.guidebook.domain.model.ResponseData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/service/v2/upcomingGuides/")
    suspend fun getData(): Response<ResponseData>
}