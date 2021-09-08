//package com.example.appsos.checkInternet
//
//import android.content.Context
//import android.net.ConnectivityManager
//import android.net.NetworkInfo
//import android.os.Build
//
//class CheckInternet {
//    fun isConnectedToInternet(context : Context) : Boolean {
//        val connectivityManager =context.getSystemService(Context.CONNECTIVITY_SERVICE)
//                as ConnectivityManager
//        if(connectivityManager != null) {
//            val info = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                arrayOf(connectivityManager.activeNetwork)
//            } else null
//            if(info != null) {
//                for(element in info) {
//                    if (element.() == NetworkInfo.State.CONNECTED) {
//                        return true
//                    }
//                }
//            }
//        }
//        return false
//    }
//
//}