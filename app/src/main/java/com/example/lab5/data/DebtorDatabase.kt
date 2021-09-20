package com.example.lab5.data

import androidx.room.Database
import androidx.room.Room
import com.example.lab5.data.entities.DebtorSession
import androidx.room.RoomDatabase
import com.example.lab5.data.dao.DebtorDao
import com.example.lab5.data.dao.DebtorSessionDao
import com.example.lab5.data.entities.Debtor

@Database(entities = [Debtor::class], version = 1)
abstract class DebtorDatabase: RoomDatabase() {
    abstract  fun DebtorDao(): DebtorDao
}

@Database(entities = [DebtorSession::class], version = 1)
abstract class DebtorSessionDatabase: RoomDatabase(){

    abstract fun DebtorSessionDao(): DebtorSessionDao
}

