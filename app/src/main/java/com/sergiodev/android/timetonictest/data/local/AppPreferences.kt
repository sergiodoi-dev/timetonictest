package com.sergiodev.android.timetonictest.data.local

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private val PREF_SESSKEY = "sesskey"
    private val PREF_USERID = "userid"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("TimetonicPreferences", Context.MODE_PRIVATE)
    }

    var sesskey: String
        get() = sharedPreferences.getString(PREF_SESSKEY, "") ?: ""
        set(value) = sharedPreferences.edit().putString(PREF_SESSKEY, value).apply()

    var userid: String
        get() = sharedPreferences.getString(PREF_USERID, "") ?: ""
        set(value) = sharedPreferences.edit().putString(PREF_USERID, value).apply()



}