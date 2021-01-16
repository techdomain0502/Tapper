package com.shiksha.android.tapper.db

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

class Repository(val context: Context) {
    private val pattern = "dd-MM-yyyy"
    var dateInString: String = SimpleDateFormat(pattern).format(Date())
    var io = Dispatchers.IO
    init {

    }

    private var database:AppDatabase =
        Room.databaseBuilder(context,AppDatabase::class.java,"taskDb")
            .allowMainThreadQueries()
            .build()

    private var taskDao: TaskDao

    init {
        taskDao = database.getTaskDao()
        CoroutineScope(io).launch {
            taskDao.insertTask(Task(0,"dummy",dateInString,0))
        }


    }


    fun getTasks():LiveData<List<Task>>{
           return taskDao.getTasks()
    }

    fun getTaskCount():LiveData<Task>{
        return taskDao.getTaskDetail(dateInString)
    }

    fun updateTask(count:Int){
        taskDao.updateTaskCount(count,dateInString)
    }

    fun updateTaskName(name:String){
        taskDao.updateTaskName(name,dateInString)
    }

}