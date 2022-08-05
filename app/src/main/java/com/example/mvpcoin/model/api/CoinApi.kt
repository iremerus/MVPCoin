package com.example.mvpcoin.model.api

import com.example.mvpcoin.interfaces.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinApi {

    companion object {
        private var retrofit:Retrofit?=null

        val client:Retrofit get() {
            if (retrofit==null) {
                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constant.BASE_URL)
                    .build()
            }
            return retrofit!!
        }
    }
}