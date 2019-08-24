package com.djelroy.jsm.persistance.application

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.djelroy.jsm.data.Application

@Dao
interface ApplicationDao {

    @Query("SELECT * from applications")
    fun getAllApplications(): LiveData<List<Application>>

    @Insert
    suspend fun insert(application: Application)

    @Query("DELETE FROM applications")
    fun deleteAll()
}