package com.shiksha.android.tapper.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shiksha.android.tapper.db.Repository
import com.shiksha.android.tapper.db.Task
import java.text.SimpleDateFormat
import java.util.*

class CounterViewModel(application: Application):AndroidViewModel(application) {
    private val counterData:MutableLiveData<Int> = MutableLiveData()
    var repository:Repository = Repository(application.applicationContext)

    fun setCounterData(data: Int){
        counterData.value = data
        repository.updateTask(data)
    }

    fun getCounterData():LiveData<Task>{
        return repository.getTaskCount()
    }
}