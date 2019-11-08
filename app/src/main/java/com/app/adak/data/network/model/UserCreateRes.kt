package com.app.adak.data.network.model


import com.google.gson.annotations.SerializedName

data class UserCreateRes(
    @SerializedName("createdAt")
    var createdAt: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("job")
    var job: String,
    @SerializedName("name")
    var name: String
)