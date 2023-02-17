package com.salim.nbastatsapp.utilities

import android.content.SharedPreferences

/***
 * Wrapper class to make testing caching easier. Remove any android stuff from testing.
 *
 * @constructor construct wrapper by passing in [SharedPreferences]
 */
class SharedPreferencesWrapper(private val sharedPreferences: SharedPreferences) {

    fun saveString(key: String, value: String) {
        sharedPreferences.edit().apply {
            putString(key, value)
            apply()
        }
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().apply {
            putBoolean(key, value)
            apply()
        }
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun saveLong(key: String, value: Long) {
        sharedPreferences.edit().apply {
            putLong(key, value)
            apply()
        }
    }

    fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, 0L)
    }

    fun saveInt(key: String, value: Int) {
        sharedPreferences.edit().apply {
            putInt(key, value)
            apply()
        }
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }
}
