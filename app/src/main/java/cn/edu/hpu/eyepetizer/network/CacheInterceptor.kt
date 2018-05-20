package cn.edu.hpu.eyepetizer.network

import android.content.Context
import android.util.Log
import cn.edu.hpu.eyepetizer.utils.LogUtils
import cn.edu.hpu.eyepetizer.utils.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Description:
 * Author:Steve
 * Email:444835397@qq.com
 * Time: 2018/5/6
 */
class CacheInterceptor(context: Context):Interceptor{
    val TAG:String = "CacheInterceptor"
    val context = context
    override fun intercept(chain: Interceptor.Chain?): Response? {
        var request = chain?.request()
        if (NetworkUtils.isNetConneted(context)){
            val response = chain?.proceed(request)
            //read from cache for 60 s
            val maxAge = 60
            val cacheControl = request?.cacheControl().toString()
            LogUtils.e(TAG,"6s load cache"+cacheControl)
            return response?.newBuilder()?.removeHeader("Pragma")?.removeHeader("Cache-Control")?.header("Cache-Control","public, max-age="+maxAge)?.build()
        }else{
            LogUtils.e(TAG,"no network load cache")
            request = request?.newBuilder()?.cacheControl(CacheControl.FORCE_CACHE)?.build()
            val response = chain?.proceed(request)
            //set cache times is 3 days
            val maxStale = 60 * 60 * 24 * 3
            return response?.newBuilder()?.removeHeader("Pragma")?.removeHeader("Cache-Control")?.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)?.build()
        }
    }

}