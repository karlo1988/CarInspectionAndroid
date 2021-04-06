package com.example.carinspection.database

import androidx.room.Entity

@Entity(tableName = "settings")
data class Settings(
    var key:String="",
    var value:String=""
)
