package com.example.carinspection.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars_inspection_flow")
data class CarsInspectionFlow(
        @PrimaryKey(autoGenerate = true)
        var id: Int=0,

        @ColumnInfo(name = "created_at")
        var createdAt: Long=System.currentTimeMillis(),

        @ColumnInfo(name = "car_id")
        var carId: Long=0,

        @ColumnInfo(name = "user_id")
        var userId: Long=0,

        var status: Byte=0,

        @ColumnInfo(name = "next_inspection_date")
        var nextInspectionDate: Long=System.currentTimeMillis(),

        var exhaust: Float=0f,

        @ColumnInfo(name = "break_quality")
        var breakQuality: Float=0f,

        @ColumnInfo(name = "visual_condition")
        var visualCondition: Float=0f,

        @ColumnInfo(name = "light_left")
        var lightLeft: Float=0f,

        @ColumnInfo(name = "light_right")
        var lightRight: Float=0f,

        @ColumnInfo(name = "turn_signal_left")
        var turnSignalLeft: Float=0f,

        @ColumnInfo(name = "turn_signal_right")
        var turnSignalRight: Float=0f,

        @ColumnInfo(name = "stop_left")
        var stopLeft: Float=0f,

        @ColumnInfo(name = "stop_right")
        var stopRight: Float=0f,

        @ColumnInfo(name = "wheel_front_left")
        var wheelFrontLeft: Float=0f,

        @ColumnInfo(name = "wheel_front_right")
        var wheelFrontRight: Float=0f,

        @ColumnInfo(name = "wheel_back_left")
        var wheelBackLeft: Float=0f,

        @ColumnInfo(name = "wheel_back_right")
        var wheelBackRight: Float=0f

)
