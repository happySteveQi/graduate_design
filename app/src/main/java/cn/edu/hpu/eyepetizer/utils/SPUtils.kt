package cn.edu.hpu.eyepetizer.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.*
import kotlin.collections.HashMap

/**
 * Description:
 * Author:Steve
 * Email:444835397@qq.com
 * Time: 2018/5/8
 */
class SPUtils private constructor(context: Context, spName: String) {
    private val sp: SharedPreferences

    init {
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
    }

    /**
     *SP 中写入String
     *
     *
     */
    fun put(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    /**
     * SP 中读取String
     *
     *
     */
    @JvmOverloads
    fun getString(key: String, defaultValue: String = ""): String {
        return sp.getString(key, defaultValue)
    }

    /**
     * SP 中写入int
     */
    fun put(key: String, value: Int) {
        sp.edit().putInt(key, value).apply()
    }

    /**
     * SP中读取int
     */
    @JvmOverloads
    fun getInt(key: String, defaultValue: Int = -1): Int {
        return sp.getInt(key, defaultValue)
    }

    /**
     * sp 中写入Long
     */
    fun put(key: String, value: Long) {
        sp.edit()
    }

    /**
     * SP 中读取long
     */
    @JvmOverloads
    fun getLong(key: String, defaultValue: Long = -1L): Long {
        return sp.getLong(key, defaultValue)
    }

    /**
     * SP 中写入float
     */
    fun put(key: String, value: Float) {
        sp.edit().putFloat(key, value).apply()
    }

    /**
     * SP 中读取float
     */
    @JvmOverloads
    fun getFloat(key: String, defaultValue: Float = -1f): Float {
        return sp.getFloat(key, defaultValue)
    }

    /**
     * sp中写入boolean
     */
    fun put(key: String, value: Boolean) {
        sp.edit().putBoolean(key, value).apply()
    }

    /**
     * SP 中读取 boolean
     */
    @JvmOverloads
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sp.getBoolean(key, defaultValue)
    }

    /**
     * SP 中写入String集合
     */
    fun put(key: String, values: Set<String>) {
        sp.edit().putStringSet(key, values).apply()
    }

    /**
     * sp 中读取 StringSet
     */
    @JvmOverloads
    fun getStringSet(key: String, defaultValue: Set<String> = Collections.emptySet()): Set<String> {
        return sp.getStringSet(key, defaultValue)
    }

    /**
     * SP 中获取所有键值对
     */
    val all: Map<String, *>
        get() = sp.all

    /**
     * SP 中移除key
     */
    fun remove(key: String) {
        sp.edit().remove(key).apply()
    }

    /**
     * SP 中是否存在该key
     */
    operator fun contains(key: String): Boolean {
        return sp.contains(key)
    }

    /**
     * SP 中清除所有数据
     */
    fun clear() {
        sp.edit().clear().apply()
    }

    companion object {
        private val sSPMap = HashMap<String, SPUtils>()

        fun getInstance(context: Context, spName: String): SPUtils {
            var spName = spName
            if (hasSpace(spName)) spName = "spUtils"
            var sp: SPUtils? = sSPMap[spName]
            if (sp == null) {
                sp = SPUtils(context, spName)
                sSPMap.put(spName, sp)
            }
            return sp
        }

        private fun hasSpace(s: String?): Boolean {
            if (s == null) return true
            var i = 0
            val len = s.length
            while (i < len) {
                if (!Character.isWhitespace(s[i])) {
                    return false
                }
                i++
            }
            return true
        }
    }
}