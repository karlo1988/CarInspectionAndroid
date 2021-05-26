package com.example.carinspection

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.carinspection.database.CarInspectionDatabase
import com.example.carinspection.database.CarInspectionDao
import com.example.carinspection.database.User
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CarInspectionDatabaseTest {
    private lateinit var sleepDao: CarInspectionDao
    private lateinit var db: CarInspectionDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, CarInspectionDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        sleepDao = db.carInspectionDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val user = User(firstName = "კარლო", lastName = "ჩხ", login = "user1234", password = "1111", isAdmin = false)
        sleepDao.insertUser(user)
        val users = sleepDao.getAllUsers()
        assertEquals(1, 1)
    }
}