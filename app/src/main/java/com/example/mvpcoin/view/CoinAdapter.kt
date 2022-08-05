package com.example.mvpcoin.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpcoin.databinding.CoinLayoutBinding
import com.example.mvpcoin.model.datamodel.CoinModel


class CoinAdapter(
    private var coins: List<CoinModel>, private var activity: MainActivity
) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    inner class CoinViewHolder(_binding: CoinLayoutBinding) : RecyclerView.ViewHolder(_binding.root) {
        private val binding: CoinLayoutBinding = _binding
        fun bind(coin: CoinModel) {
            binding.coin = coin
            binding.executePendingBindings()
            val coin: CoinModel = coins[position]
            binding.tv24h.setTextColor(if (coin.price_change_percentage_24h.contains("-"))
            { Color.parseColor("#FF0000") } else{ Color.parseColor("#32CD32") })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = CoinLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coins[position])
    }

    /**
     * coins: Mutable List of CryptoModel elements
     * Updates the UI according to the data that comes from API
     */
    fun updateData(coins: List<CoinModel>) {
        this.coins = coins
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}