package com.example.guidebook.domain.repository

import com.example.guidebook.domain.model.DataDB
import com.example.guidebook.domain.model.ResponseData
import retrofit2.Response

interface DataRepository {
    suspend fun getData(): Response<ResponseData>
}