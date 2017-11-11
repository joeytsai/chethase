package com.googlejobapp.chethase

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by joey on 8/7/17.
 */
class SharedPrefs(context: Context) {
    private val prefs: SharedPreferences = context.applicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    private fun put(key: String, value: String) = prefs.edit().putString(key, value).apply()
    private fun get(key: String): String = prefs.getString(key, "")

    var oauthToken: String
        get() = get(KEY_OAUTH_TOKEN)
        set(value) = put(KEY_OAUTH_TOKEN, value)

    var deviceId: String
        get() = get(KEY_DEVICE_ID)
        set(value) = put(KEY_DEVICE_ID, value)

    companion object {
        private val NAME = "chethase.app"
        private val KEY_OAUTH_TOKEN = "reddit.oauth.token"
        private val KEY_DEVICE_ID = "reddit.device.id"
    }
}