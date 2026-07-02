package com.anmp.habittracker_malasngoding_anmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name="username")
    val username: String,
    @ColumnInfo(name="password")
    val password: String,
){
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}
