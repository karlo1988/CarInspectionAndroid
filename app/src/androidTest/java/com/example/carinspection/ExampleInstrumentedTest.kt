package com.example.carinspection

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.carinspection.database.CarInspectionDao
import com.example.carinspection.database.CarInspectionDatabase
import com.example.carinspection.database.User
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class CarInspectionTest {

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
    fun insertAndGetUser() {
        val user = User()
        user.firstName="მთავარი"
        user.lastName="ადმინისტრატორი"
        user.login="Admin"
        user.password="admin1234"
        user.isAdmin=true;
        sleepDao.insertUser(user)
        val usr = sleepDao.getUser(1)
        assertEquals(usr?.id, 1)
    }
}