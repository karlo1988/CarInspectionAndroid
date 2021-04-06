package com.example.carinspection.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
        @PrimaryKey(autoGenerate = true)
        var id: Int=0,

        @ColumnInfo(name = "created_at")
        var createdAt: Long=System.currentTimeMillis(),

        var model: String="",

        @ColumnInfo(name = "state_number")
        var stateNumber: String="",

        @ColumnInfo(name = "vin_code")
        var vinCode: String="",

        var description: String="",

        @ColumnInfo(name = "img_url")
        var imgUrl: String=""
)