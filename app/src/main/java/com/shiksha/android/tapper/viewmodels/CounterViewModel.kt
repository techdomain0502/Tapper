package com.shiksha.android.tapper.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shiksha.android.tapper.db.Repository
import com.shiksha.android.tapper.db.Task

class CounterViewModel(application: Application):AndroidViewModel(application) {
    private val counterData:MutableLiveData<Int> = MutableLiveData()
    var repository:Repository = Repository(application.applicationContext)

    fun setCounterData(data: Int){
        counterData.value = data
        repository.updateTask(data)
    }

    fun setTaskName(name:String){
        repository.updateTaskName(name)
    }

    fun getCounterData():LiveData<Task>{
        return repository.getTaskCount()
    }
}