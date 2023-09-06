package com.example.guidebook.domain.model

import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("url") var url: String?  = null,
    @SerializedName("startDate") var startDate: String?  = null,
    @SerializedName("endDate") var endDate: String?  = null,
    @SerializedName("name") var name: String?  = null,
    @SerializedName("icon") var icon: String?  = null,
    @SerializedName("objType") var objType: String?  = null,
    @SerializedName("loginRequired") var loginRequired : Boolean? = null

)
