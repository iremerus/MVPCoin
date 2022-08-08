package com.example.mvpcoin.interfaces

import android.content.Context
import com.example.mvpcoin.model.datamodel.BaseModel

interface CoinInterface {
    interface CoinModel{
        fun getCoins(presenter: CoinPresenter): List<com.example.mvpcoin.model.datamodel.CoinModel>
        fun checkInternet(context: Context): Boolean
    }
    interface CoinView{
        fun updateViewData()
    }
    interface CoinPresenter{
        fun UIAutoUpdate()
        fun showCoins(): List<BaseModel>
        fun networkUI(context: Context): Boolean
    }

}