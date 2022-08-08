package com.example.mvpcoin.model.api

import com.example.mvpcoin.interfaces.Constant
import com.example.mvpcoin.model.datamodel.CoinModel
import retrofit2.Call
import retrofit2.http.GET

interface JobServices {
    @GET(Constant.COINS_URL)
    fun getCoinList() : Call<MutableList<CoinModel>>
}