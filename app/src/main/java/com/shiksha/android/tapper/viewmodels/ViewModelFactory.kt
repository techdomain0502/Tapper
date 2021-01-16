package com.shiksha.android.tapper.viewmodels

import android.app.ActivityManager
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import java.lang.RuntimeException

class ViewModelFactory(private val application: Application) :ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass){
            CounterViewModel::class.java ->{
                 CounterViewModel(application) as T
            }

            LockViewModel::class.java ->{
                LockViewModel() as T
            }
            else ->{
                 throw RuntimeException("no such viewmodel")
            }
        }
    }

}