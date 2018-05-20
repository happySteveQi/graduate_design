package cn.edu.hpu.eyepetizer.mvp.model

import android.content.Context
import cn.edu.hpu.eyepetizer.mvp.model.bean.HomeBean
import cn.edu.hpu.eyepetizer.network.ApiService
import cn.edu.hpu.eyepetizer.network.RetrofitClient
import io.reactivex.Observable

/**
 * Description:
 * Author:Steve
 * Email:444835397@qq.com
 * Time: 2018/5/6
 */
class HomeModel{
    fun loadData(context: Context,isFirst:Boolean,data:String?): Observable<HomeBean>? {
        val retrofitClient = RetrofitClient.getInstance(context,ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        when(isFirst){
            true -> return apiService?.getHomeData()

            false -> return apiService?.getHomeMoreData(data.toString(),"2")
        }
    }
}