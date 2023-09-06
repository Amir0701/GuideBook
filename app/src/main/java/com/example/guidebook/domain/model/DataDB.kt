package com.example.guidebook.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "data")
data class DataDB(
    @PrimaryKey
    var url: String,
    var startDate: String?  = null,
    var endDate: String?  = null,
    var name: String?  = null,
    var icon: String?  = null,
    var objType: String?  = null,
    var loginRequired : Boolean? = null
)