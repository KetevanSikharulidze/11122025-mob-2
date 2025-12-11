package com.example.a11122025_mob_2

import com.google.gson.annotations.SerializedName

data class DataModel(

    @SerializedName("category")
    val categories: List<String>?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val url: String,
    val value: String

) : java.io.Serializable