package cn.edu.hpu.eyepetizer.mvp.contract

import cn.edu.hpu.eyepetizer.base.BasePresenter
import cn.edu.hpu.eyepetizer.base.BaseView
import cn.edu.hpu.eyepetizer.mvp.model.bean.HomeBean

/**
 * Description:
 * Author:Steve
 * Email:444835397@qq.com
 * Time: 2018/5/6
 */
interface HomeContract {
    interface View : BaseView<Presenter> {
        fun setData(bean: HomeBean)
    }

    interface Presenter : BasePresenter {
        fun requestData()
    }
}