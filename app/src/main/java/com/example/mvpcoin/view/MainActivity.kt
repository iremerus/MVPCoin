package com.example.mvpcoin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvpcoin.databinding.ActivityMainBinding
import com.example.mvpcoin.interfaces.CoinInterface
import com.example.mvpcoin.model.datamodel.BaseModel
import com.example.mvpcoin.presenter.CoinPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CoinInterface.CoinView {
    private lateinit var coinAdapter: CoinAdapter
    private lateinit var binding: ActivityMainBinding
    private var presenter:CoinPresenter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = CoinPresenter(this)
        coinAdapter = CoinAdapter(mutableListOf(), this)
        rv_coins.adapter = coinAdapter
        rv_coins.layoutManager = LinearLayoutManager(this)

        presenter?.UIAutoUpdate()
    }

    /**
     * checks whether device is connected to the internet
     * if so, updates the adapter, else displays message
     */
    override fun updateViewData() {
        if(presenter?.networkUI(this) == true){
            coinAdapter.updateData(presenter?.showCoins() as ArrayList<BaseModel>)
        } else {
            CustomDialogFragment().show(supportFragmentManager, "customDialog")
        }
    }
}