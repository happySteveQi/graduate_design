package cn.edu.hpu.eyepetizer.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.Toast
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Description:
 * Author:Steve
 * Email:444835397@qq.com
 * Time: 2018/5/10
 */
fun Context.showToast(msg: String): Toast {
    var toast: Toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
    return toast
}

inline fun <reified T : Activity> Activity.newIntent() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return subscribeOn(Schedulers.io()).
            unsubscribeOn(Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread())
}

