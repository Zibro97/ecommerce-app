package com.zibro.ecommerce.data.datasource

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceDataSource @Inject constructor(
    @ApplicationContext context: Context,
) {
    companion object {
        private const val PREFERENCE_NAME = "preference_name"
        private const val ACCOUNT_INFO = "account_info"
    }

    private fun getPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private val prefs by lazy { getPreference(context) }
    private val editor by lazy { prefs.edit() }
    private val gson = Gson()

    private fun putString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    private fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun putInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    private fun getString(key: String, defValue : String? = null): String? {
        return prefs.getString(key, defValue)
    }

    private fun getBoolean(key: String, defValue : Boolean = false): Boolean {
        return prefs.getBoolean(key, defValue)
    }

    private fun getInt(key: String, defValue : Int = 0): Int {
        return prefs.getInt(key, defValue)
    }
}