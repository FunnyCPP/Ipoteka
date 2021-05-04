package com.codart.ipoteka.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codart.ipoteka.data.entities.Token

@Database(entities = [Token::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun tokenDao(): TokenDao
    abstract fun userTokenDao(): UserTokenDao
    companion object{
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
                instance ?: synchronized(this){ instance ?: buildDatabase(context).also{ instance = it}}
        private fun buildDatabase(appContext: Context) =
                Room.databaseBuilder(appContext, AppDatabase::class.java, "appdb")
                        .fallbackToDestructiveMigration()
                        .build()
    }
}