package com.djelroy.jsm.persistance.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.djelroy.jsm.data.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Application::class], version = 1)
abstract class ApplicationRoomDatabase: RoomDatabase() {
    abstract fun applicationDao(): ApplicationDao

    companion object {
        @Volatile
        private var INSTANCE: ApplicationRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ApplicationRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ApplicationRoomDatabase::class.java,
                        "application_database")
                        .addCallback(ApplicationDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class ApplicationDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.applicationDao())
                }
            }
        }

        suspend fun populateDatabase(dao: ApplicationDao){
            dao.deleteAll()

            dao.insert(Application("Ludia", "Java Developer", "Le candidat idéal possède de l’expérience en construction et en optimisation de systèmes de mégadonnées."))
            dao.insert(Application("SIMO", "Java Backend Developer", "Le candidat idéal possède de l’expérience en construction et en optimisation de systèmes de mégadonnées. Il collaborera étroitement avec nos analystes et nos experts en science des données afin d’aider à diriger le flux de données dans le pipeline et à assurer l’harmonisation de la livraison et de l’utilisation des données dans le cadre de projets multiples"))
        }
    }
}
