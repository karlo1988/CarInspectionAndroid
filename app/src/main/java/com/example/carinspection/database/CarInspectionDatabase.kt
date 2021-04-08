package com.example.carinspection.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class, Car::class, CarsInspectionFlow::class, Settings::class], version = 1,  exportSchema = false)
abstract class CarInspectionDatabase :RoomDatabase(){

 abstract val carInspectionDao:CarInspectionDao

    companion object {

        @Volatile
        private var INSTANCE: CarInspectionDatabase? = null

        fun getInstance(context: Context): CarInspectionDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        CarInspectionDatabase::class.java,
                        "car_inspection_database")
                        .addCallback(seedDatabaseCallback(context))
                        .build()

        private fun seedDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    ioThread {
                        var carInspectionDao = getInstance(context)!!.carInspectionDao
                        var user=User()
                        user.firstName="მთავარი"
                        user.lastName="ადმინისტრატორი"
                        user.login="Admin"
                        user.password="admin1234"
                        user.isAdmin=true;
                        carInspectionDao.insertUser(user)
                    }
                }
            }
        }
    }
}