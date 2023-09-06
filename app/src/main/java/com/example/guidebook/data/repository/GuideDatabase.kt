package com.example.guidebook.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.guidebook.domain.model.DataDB

@Database(entities = [DataDB::class], version = 1)
abstract class GuideDatabase: RoomDatabase() {
    abstract val dao: Dao

    companion object{
        const val DB_NAME = "GuideDB"
    }
}