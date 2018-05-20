package cn.edu.hpu.eyepetizer.mvp.presenter

import android.content.Context
import cn.edu.hpu.eyepetizer.mvp.contract.HomeContract
import cn.edu.hpu.eyepetizer.mvp.model.HomeModel
import cn.edu.hpu.eyepetizer.mvp.model.bean.HomeBean
import cn.edu.hpu.eyepetizer.utils.applySchedulers
import io.reactivex.Observable

/**
 * Description:
 * Author:Steve
 * Email:444835397@qq.com
 * Time: 2018/5/6
 */
class HomePresenter(context: Context, view: HomeContract.View) : HomeContract.Presenter {
    var mContext: Context? = null
    var mView: HomeContract.View? = null
    val mModel: HomeModel by lazy {
        HomeModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun requestData() {
        val observable: Observable<HomeBean>? = mContext?.let { mModel.loadData(it, true, "0") }
        observable?.applySchedulers()?.subscribe { homeBean: HomeBean ->
            mView?.setData(homeBean)
        }
    }

    override fun start() {
        requestData()
    }

    fun moreData(data: String?) {
        val observable: Observable<HomeBean>? = mContext?.let { mModel.loadData(it, false, data) }
        observable?.applySchedulers()?.subscribe { homeBean: HomeBean ->
            mView?.setData(homeBean)
        }
    }
}