package com.example.guidebook.domain.model

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
    @SerializedName("total") var total: String? = null
)
