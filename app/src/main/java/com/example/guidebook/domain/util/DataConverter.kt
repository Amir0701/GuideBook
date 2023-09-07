package com.example.guidebook.domain.util


import com.example.guidebook.domain.model.Data
import com.example.guidebook.domain.model.DataDB

class DataConverter {
    fun toDataDB(data: Data): DataDB{
        return DataDB(
            url = data.url,
            startDate = data.startDate,
            endDate = data.endDate,
            name = data.name,
            icon = data.icon,
            objType = data.objType,
            loginRequired = data.loginRequired
        )
    }
}