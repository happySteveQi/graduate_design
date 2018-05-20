package cn.edu.hpu.eyepetizer.utils

import android.util.Log
import com.facebook.stetho.common.LogUtil

/**
 * Description:
 * Author:Steve
 * Email:444835397@qq.com
 * Time: 2018/5/6
 */
class LogUtils{
    companion object {
        var DEBUG:Boolean = true
        fun  e(tag:String,msg:String){
            if (DEBUG) Log.e(tag,msg)
        }
        fun w(tag:String,msg:String){
            if (DEBUG) Log.e(tag,msg)
        }
        fun i(tag:String,msg:String){
            if (DEBUG) Log.e(tag,msg)
        }
        fun d(tag:String,msg:String){
            if (DEBUG) Log.e(tag,msg)
        }
        fun v(tag:String,msg:String){
            if (DEBUG) Log.e(tag,msg)
        }
    }

}