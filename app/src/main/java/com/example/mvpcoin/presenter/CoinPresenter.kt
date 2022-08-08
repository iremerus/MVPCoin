package com.example.mvpcoin.presenter

import android.content.Context
import com.example.mvpcoin.interfaces.CoinInterface
import com.example.mvpcoin.model.datamodel.AdModel
import com.example.mvpcoin.model.datamodel.BaseModel
import com.example.mvpcoin.model.datamodel.CoinModel
import com.example.mvpcoin.model.repos.CoinRepos

class CoinPresenter(coinview: CoinInterface.CoinView) : CoinInterface.CoinPresenter {
    private var view: CoinInterface.CoinView = coinview
    private var model: CoinInterface.CoinModel = CoinRepos()

    /**
     * @return List of CoinModel
     * returns the coin data from the api
     */
    override fun showCoins(): List<BaseModel> {
        val newItems = ArrayList<BaseModel>()
        newItems.addAll(model.getCoins(this))
        val adModel = AdModel("url")
        if (newItems.size > 5) {
            newItems.add(5, adModel)
        } else {
            newItems.add(newItems.size, adModel)
        }
        return newItems
    }

    /**
     * context: MainActivity
     * @return Boolean, whether there is internet connection
     * Checks the internet connection in the MainActivity
     */
    override fun networkUI(context: Context): Boolean {
        return model.checkInternet(context)
    }

    override fun UIAutoUpdate() {
        view.updateViewData()
    }
}