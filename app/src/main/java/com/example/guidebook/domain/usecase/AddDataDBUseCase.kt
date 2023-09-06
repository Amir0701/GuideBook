package com.example.guidebook.domain.usecase

import com.example.guidebook.domain.model.DataDB
import com.example.guidebook.domain.repository.DataDBRepository

class AddDataDBUseCase(
    val dataDBRepository: DataDBRepository
) {
    suspend operator fun invoke(dataDB: DataDB){
        dataDBRepository.addData(dataDB)
    }
}