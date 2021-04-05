package com.example.carinspection.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
        @PrimaryKey(autoGenerate = true)
        var id: Int=0,

        @ColumnInfo(name = "created_at")
        var createdAt: Long=System.currentTimeMillis(),

        @ColumnInfo(name = "first_name")
        var firstName: String="",

        @ColumnInfo(name = "last_name")
        var lastName: String="",

        var login: String="",

        var password: String="",

        var note: String="",

        @ColumnInfo(name = "img_url")
        var imgUrl: String="",

        @ColumnInfo(name = "is_admin")
        var isAdmin: Boolean=false
)