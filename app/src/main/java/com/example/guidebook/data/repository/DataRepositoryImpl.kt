package com.example.guidebook.data.repository

import com.example.guidebook.domain.model.ResponseData
import com.example.guidebook.domain.repository.DataRepository
import com.example.guidebook.domain.service.ApiService
import retrofit2.Response

class DataRepositoryImpl(val apiService: ApiService): DataRepository {
    override suspend fun getData(): Response<ResponseData> {
        return apiService.getData()
    }
}