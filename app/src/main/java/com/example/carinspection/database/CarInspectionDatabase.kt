package com.example.carinspection.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class], version = 1,  exportSchema = false)
abstract class CarInspectionDatabase :RoomDatabase() {

    abstract val carInspectionDao: CarInspectionDao

    companion object {

        @Volatile
        private var INSTANCE: CarInspectionDatabase? = null

      /*  fun getInstance(context: Context): CarInspectionDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {
                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            CarInspectionDatabase::class.java,
                            "car_inspection_database"
                    )
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this lesson. You can learn more about
                            // migration with Room in this blog post:
                            // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
                            .fallbackToDestructiveMigration()
                            .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }*/

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