package com.jdjr.jdcn.eyepetizer_kotlin.ui

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import cn.edu.hpu.eyepetizer.R
import cn.edu.hpu.eyepetizer.adapter.HomeAdapter
import cn.edu.hpu.eyepetizer.mvp.contract.HomeContract
import cn.edu.hpu.eyepetizer.mvp.model.bean.HomeBean
import cn.edu.hpu.eyepetizer.mvp.model.bean.HomeBean.IssueListBean.ItemListBean
import cn.edu.hpu.eyepetizer.mvp.presenter.HomePresenter
import kotlinx.android.synthetic.main.home_fragment.*
import java.util.*
import java.util.regex.Pattern

/**
 * Description:
 * Author:Steve
 * Email:444835397@qq.com
 * Time: 2017/12/10
 */
class HomeFragment : BaseFragment(), HomeContract.View, SwipeRefreshLayout.OnRefreshListener {


    var mIsRefresh: Boolean = false
    var mPresenter: HomePresenter? = null
    var mList = ArrayList<ItemListBean>()
    var mAdapter: HomeAdapter? = null
    var data: String? = null
    override fun setData(bean: HomeBean) {
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(bean?.nextPageUrl)
        data = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
            if (mList.size > 0) {
                mList.clear()
            }

        }
        bean.issueList!!.flatMap { it.itemList!! }.filter { it.type.equals("video") }
                    .filter { it.type.equals("video") }
                    .forEach {  mList.add(it)}
        mAdapter?.notifyDataSetChanged()
    }

    override fun initView() {
        mPresenter = HomePresenter(context, this)
        mPresenter?.start()
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = HomeAdapter(context, mList)
        recyclerView.adapter = mAdapter
        refreshLayout.setOnRefreshListener(this)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                var lastPosition = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPosition == mList.size - 1) {
                    if (data != null){
                        mPresenter?.moreData(data)
                    }
                }
            }
        })
    }

    override fun getLayoutResources(): Int {
        return R.layout.home_fragment
    }

    override fun onRefresh() {
        if (!mIsRefresh) {
            mIsRefresh = true
            mPresenter?.start()
        }
    }
}