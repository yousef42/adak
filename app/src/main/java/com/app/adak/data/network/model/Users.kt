package com.app.adak.data.network.model


import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("data")
    val `data`: ArrayList<Data>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
) {
    data class Data(
        @SerializedName("avatar")
        val avatar: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("first_name")
        var firstName: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("last_name")
        var lastName: String
    )
}