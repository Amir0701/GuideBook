package com.example.guidebook.domain.usecase

import com.example.guidebook.domain.model.ResponseData
import com.example.guidebook.domain.repository.DataRepository
import retrofit2.Response

class GetDataUseCase(
    val dataRepository: DataRepository
) {
    suspend operator fun invoke(): Response<ResponseData>{
        return dataRepository.getData()
    }
}