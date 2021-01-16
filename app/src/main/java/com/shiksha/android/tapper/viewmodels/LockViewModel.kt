package com.shiksha.android.tapper.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LockViewModel:ViewModel() {

    enum class Visibility{
        LOCKED,UNLOCKED
    }
    val lockData: MutableLiveData<Visibility> = MutableLiveData()

    fun setLockViewData(data:Visibility){
        lockData.value = data
    }

    fun getLockViewData(): LiveData<Visibility> {
        return lockData
    }



}