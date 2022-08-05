package com.example.mvpcoin.model.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

class Network {
    companion object {
        /**
         * context: Context
         * @return Boolean, true if connected to the internet, false otherwise
         * Checks whether the device is connected to the internet
         */
        fun isConnected(context: Context): Boolean {
            var result = false
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    result = checkNetwork(this, this.activeNetwork)
                }else {
                    val networks = this.allNetworks
                    for (network in networks) {
                        if (checkNetwork (this, network)) {
                            result = true
                        }
                    }
                }
            }
            return result
        }

        /**
         * connectivityManager: ConnectivityManager
         * network: Network?
         * @return: Boolean, whether the device is connected to the internet
         */
        private fun checkNetwork(connectivityManager: ConnectivityManager, network: Network?): Boolean {
            connectivityManager.getNetworkCapabilities(network)?.also {
                if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || it.hasTransport(
                        NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                }
            }
            return false
        }
    }
}