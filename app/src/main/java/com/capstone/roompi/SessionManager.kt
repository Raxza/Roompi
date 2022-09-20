package com.capstone.roompi

import android.content.Context

class SessionManager (context: Context) {
    companion object{
        private const val ID = "id"
    }

    private val preferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun setUser(id: String) {
        val editor = preferences.edit()
        editor.putString(ID, id)
        editor.apply()
    }

    fun getUser(): String? {
        return preferences.getString(ID, null)
    }
}