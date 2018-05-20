package cn.edu.hpu.eyepetizer.network

import cn.edu.hpu.eyepetizer.mvp.model.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Description:
 * Author:Steve
 * Email:444835397@qq.com
 * Time: 2018/5/7
 */
interface ApiService{
    companion object {
        val BASE_URL : String
            get() = "http://baobab.kaiyanapp.com/api/"
    }

    //获取首页第一页数据
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHomeData():Observable<HomeBean>

    //获取首页第一页之后的数据 ?date=1499043600000&num=2
    @GET("v2/feed")
    fun getHomeMoreData(@Query("date") date:String, @Query("num") num:String) :Observable<HomeBean>

    //获取发现频道信息
//    @GET()
//    fun getFindData():Observable<MutableList<>>
}