package com.jdjr.jdcn.eyepetizer_kotlin

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import cn.edu.hpu.eyepetizer.R
import cn.edu.hpu.eyepetizer.mvp.contract.HomeContract
import cn.edu.hpu.eyepetizer.ui.fragment.FindFragment
import cn.edu.hpu.eyepetizer.ui.fragment.HotFragment
import cn.edu.hpu.eyepetizer.ui.fragment.MineFragment
import com.gyf.barlibrary.ImmersionBar
import com.jdjr.jdcn.eyepetizer_kotlin.ui.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    var homeFragment: HomeFragment? = null
    var findFragment: FindFragment? = null
    var hotFragment: HotFragment? = null
    var mineFragment: MineFragment? = null
    var mExitTime: Long = 0
    var toast: Toast? = null
    //        lateinit var searchFragment:SearchFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImmersionBar.with(this).transparentBar()
                .barAlpha(0.3f)
                .fitsSystemWindows(true)
                .init()
        val window = window
        val params = window.attributes
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window.attributes = params

        setRadioButton()
        initToolbar()
        initFragment(savedInstanceState)
    }

    private fun setRadioButton() {
        rb_home.isChecked = true
        rb_home.setTextColor(resources.getColor(R.color.black))
        rb_home.setOnClickListener(this)
        rb_find.setOnClickListener(this)
        rb_hot.setOnClickListener(this)
        rb_mine.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        clearState()
        when (v?.id) {
            R.id.rb_find -> {
                rb_find.isChecked = true
                rb_find.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(findFragment)
                        .hide(homeFragment)
                        .hide(mineFragment)
                        .hide(hotFragment)
                        .commit()
                tv_bar_title.text = "Discover"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.mipmap.icon_search)
            }
            R.id.rb_home -> {
                rb_home.isChecked = true
                rb_home.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(homeFragment)
                        .hide(findFragment)
                        .hide(mineFragment)
                        .hide(hotFragment)
                        .commit()
                tv_bar_title.text = getDayOfWeek()
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.mipmap.icon_search)
            }
            R.id.rb_hot -> {
                rb_hot.isChecked = true
                rb_hot.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(hotFragment)
                        .hide(findFragment)
                        .hide(mineFragment)
                        .hide(homeFragment)
                        .commit()
                tv_bar_title.text = "Ranking"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.mipmap.icon_search)
            }
            R.id.rb_mine -> {
                rb_mine.isChecked = true
                rb_mine.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(mineFragment)
                        .hide(findFragment)
                        .hide(mineFragment)
                        .hide(hotFragment)
                        .commit()
                tv_bar_title.text = getDayOfWeek()
                tv_bar_title.visibility = View.INVISIBLE
                iv_search.setImageResource(R.mipmap.icon_setting)
            }
        }
    }

    private fun clearState() {
        rg_root.clearCheck()
        rb_home.setTextColor(resources.getColor(R.color.gray))
        rb_mine.setTextColor(resources.getColor(R.color.gray))
        rb_hot.setTextColor(resources.getColor(R.color.gray))
        rb_find.setTextColor(resources.getColor(R.color.gray))
    }

    private fun initToolbar() {
        var dayOfWeek = getDayOfWeek()
        tv_bar_title.text = dayOfWeek
        tv_bar_title.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        iv_search.setOnClickListener {
            if (rb_mine.isChecked) {
                //todo 点击设置
            } else {
                //todo 点击搜索 跳转出现搜索界面
//                searchFragment
            }
        }
    }

    private fun getDayOfWeek(): String {
        var list = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        var date: Date = Date()
        var calendar: Calendar = Calendar.getInstance()
        calendar.time = date
        var index: Int = calendar.get(Calendar.DAY_OF_WEEK) - 1
        if (index < 0) index = 0
        return list[index]
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            //异常情况

            val mFragments: List<Fragment> = supportFragmentManager.fragments
            for (item in mFragments) {
                if (item is HomeFragment) {
                    homeFragment = item
                }
                if (item is FindFragment) {
                    findFragment = item
                }
                if (item is HotFragment) {
                    hotFragment = item
                }
                if (item is MineFragment) {
                    mineFragment = item
                }
            }
        } else {
            homeFragment = HomeFragment()
            findFragment = FindFragment()
            mineFragment = MineFragment()
            hotFragment = HotFragment()

            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fl_content, homeFragment)
            fragmentTrans.add(R.id.fl_content, findFragment)
            fragmentTrans.add(R.id.fl_content, mineFragment)
            fragmentTrans.add(R.id.fl_content, hotFragment)
            fragmentTrans.commit()
        }

        supportFragmentManager.beginTransaction()
                .show(homeFragment)
                .hide(findFragment)
                .hide(hotFragment)
                .hide(mineFragment)
                .commit()
    }

}
