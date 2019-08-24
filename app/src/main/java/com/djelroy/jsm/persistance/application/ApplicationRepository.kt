package com.djelroy.jsm.persistance.application

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.djelroy.jsm.data.Application

class ApplicationRepository(private val applicationDao: ApplicationDao) {
    val allApplications: LiveData<List<Application>> = applicationDao.getAllApplications()

    @WorkerThread
    suspend fun insert(application: Application){
        applicationDao.insert(application)
    }
}