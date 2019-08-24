package com.djelroy.jsm.view.application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.djelroy.jsm.data.Application
import com.djelroy.jsm.persistance.application.ApplicationRoomDatabase
import com.djelroy.jsm.persistance.application.ApplicationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApplicationViewModel(application: android.app.Application): AndroidViewModel(application) {
    private val applicationRepository: ApplicationRepository
    val allApplications: LiveData<List<Application>>

    init {
        val applicationDao = ApplicationRoomDatabase.getDatabase(application, viewModelScope).applicationDao()
        applicationRepository = ApplicationRepository(applicationDao)

        allApplications = applicationRepository.allApplications
    }

    fun insert(application: Application) = viewModelScope.launch(Dispatchers.IO){
        applicationRepository.insert(application)
    }

}