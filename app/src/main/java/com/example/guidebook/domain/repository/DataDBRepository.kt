package com.example.guidebook.domain.repository

import com.example.guidebook.domain.model.DataDB

interface DataDBRepository {
    suspend fun addData(dataDB: DataDB)
}