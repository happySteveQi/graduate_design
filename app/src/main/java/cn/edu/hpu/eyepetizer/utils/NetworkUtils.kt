package cn.edu.hpu.eyepetizer.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Description:
 * Author:Steve
 * Email:444835397@qq.com
 * Time: 2018/5/6
 */
object NetworkUtils {
    fun isNetConneted(context: Context): Boolean {
        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectManager.activeNetworkInfo
        if (networkInfo == null){
            return false
        }else{
            return networkInfo.isAvailable
        }
    }
    fun isNetConneted(context: Context,typeMobile:Int):Boolean{
        if (!isNetConneted(context)){
            return false
        }
        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo = connectManager.getNetworkInfo(typeMobile)
        if (networkInfo == null){
            return false
        }else{
            return networkInfo.isConnected&& networkInfo.isAvailable
        }
    }
    fun isPhoneNetConnected(context: Context):Boolean{
        val typeMobile = ConnectivityManager.TYPE_MOBILE
        return isNetConneted(context,typeMobile)
    }
    fun isWifiNetConnected(context: Context):Boolean{
        val typeMobile = ConnectivityManager.TYPE_WIFI
        return isNetConneted(context, typeMobile)
    }
}