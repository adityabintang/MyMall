package com.bintang.myapplication.session

import android.content.Context
import android.content.SharedPreferences

class SessionManager(val context: Context) {
    var PREF: SharedPreferences? = null
    var EDITOR: SharedPreferences.Editor? = null
    var PREF_NAME = "login"
    var IS_LOGIN = "isLogin"
    var TOKEN = "access_token"
    var EMAIL = "email"
    var ID = "id"
    var NAME = "name"
    var IMAGE = "image"

    init {
        PREF = context.getSharedPreferences(PREF_NAME, 0)
        EDITOR = PREF?.edit()
    }

    var login: Boolean?
        get() = PREF?.getBoolean(IS_LOGIN, false)
        set(login) {
            EDITOR?.putBoolean(IS_LOGIN, true)
            EDITOR?.commit()
        }

    var token: String?
        get() = PREF?.getString(TOKEN, "")
        set(token) {
            EDITOR?.putString(TOKEN, token)
            EDITOR?.commit()
        }
    var email: String?
        get() = PREF?.getString(EMAIL, "")
        set(email) {
            EDITOR?.putString(EMAIL, email)
            EDITOR?.commit()
        }

    var id: Int?
        get() = PREF?.getInt(ID, 0)
        set(id) {
            id?.let { EDITOR?.putInt(ID, it) }
            EDITOR?.commit()
        }

    var name: String?
        get() = PREF?.getString(NAME, "")
        set(name) {
            EDITOR?.putString(NAME, name)
            EDITOR?.commit()

        }
    var image: Any?
        get() = PREF?.getString(IMAGE, "")
        set(image) {
            EDITOR?.putString(IMAGE, image.toString())
            EDITOR?.commit()

        }

}