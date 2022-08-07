package com.example.mvpcoin.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpcoin.databinding.AdLayoutBinding
import com.example.mvpcoin.databinding.CoinLayoutBinding
import com.example.mvpcoin.model.datamodel.CoinModel

class CoinAdapter(
    private var coins: MutableList<CoinModel>, private var activity: MainActivity
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VT_COIN = 0
        private const val VT_AD = 1
    }

    inner class CoinViewHolder(_binding: CoinLayoutBinding) : RecyclerView.ViewHolder(_binding.root) {
        private val binding: CoinLayoutBinding = _binding
        fun bind(coin: CoinModel) {
            binding.coin = coin
            binding.executePendingBindings()
            binding.tv24h.setTextColor(if (coin.price_change_percentage_24h.contains("-"))
            { Color.parseColor("#FF0000") } else{ Color.parseColor("#32CD32") })
        }
    }

    inner class AdViewHolder(_binding: AdLayoutBinding):RecyclerView.ViewHolder(_binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VT_AD){
            val binding = AdLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AdViewHolder(binding)
        } else {
            val binding = CoinLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CoinViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is CoinViewHolder -> holder.bind(coins[position])
        }
    }

    /**
     * coins: Mutable List of CryptoModel elements
     * Updates the UI according to the data that comes from API
     */
    fun updateData(coins: MutableList<CoinModel>) {
        this.coins = coins
        if (coins.size > 5){
            coins.add(5, CoinModel("Name", "0", "0", "Null"))
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    override fun getItemViewType(position: Int): Int{
        return if (position == 5) {
            VT_AD
        } else {
            VT_COIN
        }
    }
}