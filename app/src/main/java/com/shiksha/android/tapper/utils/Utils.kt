package com.shiksha.android.tapper.utils

import android.content.Context
import android.preference.PreferenceManager

class Utils {

    companion object{
        fun savePreference(key:String,value:Boolean,context: Context){
            val preferenceEditor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            preferenceEditor.putBoolean(key,value)
            preferenceEditor.commit()
        }

        fun getPreference(key:String,context:Context):Boolean{
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key,false)
        }
    }
}