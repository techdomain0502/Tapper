package com.shiksha.android.tapper.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,

    @ColumnInfo(name="name")
var name:String="",

@ColumnInfo(name="date")
var date:String="",

@ColumnInfo(name="count")
var count:Int = 0
)