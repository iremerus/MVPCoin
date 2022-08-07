package com.example.mvpcoin.model.repos

import android.content.Context
import android.util.Log
import com.example.mvpcoin.interfaces.CoinInterface
import com.example.mvpcoin.model.api.CoinApi
import com.example.mvpcoin.model.api.JobServices
import com.example.mvpcoin.model.datamodel.CoinModel
import com.example.mvpcoin.model.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinRepos: CoinInterface.CoinModel {
    private var apiclient: JobServices?=null
    private var coins: MutableList<CoinModel> = mutableListOf()

    init {
        apiclient=CoinApi.client.create(JobServices::class.java)
    }

    /**
     * presenter: CoinPresenter
     * @return List of coin model
     * gets the coin data from api
     */
    override fun getCoins(presenter: CoinInterface.CoinPresenter): MutableList<CoinModel> {
        val call = apiclient?.getCoinList()
        call?.enqueue(object : Callback<MutableList<CoinModel>>{
            override fun onFailure(call: Call<MutableList<CoinModel>>, t: Throwable) {
                Log.e("error",t.toString())
            }

            override fun onResponse(
                call: Call<MutableList<CoinModel>>,
                response: Response<MutableList<CoinModel>>
            ) {
                if (response.isSuccessful){
                    coins = response.body()!!
                    presenter.UIAutoUpdate()
                }
            }
        })
        return coins
    }

    /**
     * Checks whether the internet is connected
     */
    override fun checkInternet(context: Context): Boolean {
        return Network.isConnected(context)
    }
}