package com.example.guidebook.data.repository

import com.example.guidebook.data.GuideDatabase
import com.example.guidebook.domain.model.DataDB
import com.example.guidebook.domain.repository.DataDBRepository

class DataDBRepositoryImpl(val db: GuideDatabase): DataDBRepository {
    override suspend fun addData(dataDB: DataDB) {
        db.dao.addData(dataDB)
    }

}