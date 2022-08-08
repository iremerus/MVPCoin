package com.example.mvpcoin.model.datamodel

import com.google.gson.annotations.SerializedName

data class CoinModel(
    @SerializedName("name") val name: String,
    @SerializedName("current_price") val current_price: String,
    @SerializedName("price_change_percentage_24h") val price_change_percentage_24h: String,
    @SerializedName("image") var image: String
):BaseModel

