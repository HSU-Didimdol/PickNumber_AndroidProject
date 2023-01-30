package com.example.picknumber_androidproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 3)
abstract class BankDataBase : RoomDatabase() {

    companion object {
        private const val dbName = "localDB"
        private var INSTANCE: BankDataBase? = null
        fun getInstance(context: Context): BankDataBase? {
            if (INSTANCE == null) {
                synchronized(BankDataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BankDataBase::class.java,
                        dbName
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}