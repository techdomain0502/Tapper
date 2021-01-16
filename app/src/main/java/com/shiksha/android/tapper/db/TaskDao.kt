package com.shiksha.android.tapper.db

import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task:Task)

    @Delete
    fun deleteTask(task:Task)

    @Query("SELECT * FROM tasks")
    fun getTasks():LiveData<List<Task>>

    @Query("SELECT * FROM tasks where date like :date")
    fun getTaskDetail(date:String):LiveData<Task>


    @Query("UPDATE tasks SET count=:count where date like :date")
    fun updateTaskCount(count:Int,date:String)

    @Query("UPDATE tasks SET name=:name where date like :date")
    fun updateTaskName(name:String,date:String)


}