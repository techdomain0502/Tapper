package com.shiksha.android.tapper.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shiksha.android.tapper.utils.Utils

class LockViewModel(val application: Application) : ViewModel() {

    enum class Visibility {
        LOCKED, UNLOCKED
    }

    val lockData: MutableLiveData<Visibility> = MutableLiveData()

    fun setLockViewData(data: Visibility) {
        lockData.value = data
        if (data == Visibility.LOCKED)
            Utils.savePreference("lockedState", true, application.applicationContext)
        else
            Utils.savePreference("lockedState", false, application.applicationContext)

    }

    fun getLockViewData(): LiveData<Visibility> {
        val visibility = Utils.getPreference("lockedState",application.applicationContext)
        if(visibility == true){
            lockData.value = Visibility.LOCKED
        }else{
            lockData.value = Visibility.UNLOCKED
        }
        return lockData
    }


}