package com.example.mvpcoin.interfaces

interface Constant {
    companion object{
        const val BASE_URL = "https://api.coingecko.com"
        const val COINS_URL = "/api/v3/coins/markets?vs_currency=usd"
    }
}