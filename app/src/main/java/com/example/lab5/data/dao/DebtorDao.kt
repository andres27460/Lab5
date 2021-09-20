package com.example.lab5.data.dao

import androidx.room.*
import com.example.lab5.data.entities.Debtor
import com.example.lab5.data.entities.DebtorSession

@Dao
interface DebtorDao {

    @Insert
    fun createDebtor(debtor: Debtor)

    @Query("SELECT * FROM table_debtor")
    fun getDebtors() : MutableList<Debtor>

    @Query("SELECT * FROM table_debtor WHERE name LIKE :name")
    fun readDebtor(name: String) : Debtor

    @Delete
    fun deleteDebtor(debtor: Debtor)

    @Update
    fun updateDebtor(debtor: Debtor)


}

@Dao
interface DebtorSessionDao{
    @Insert
    fun createUserLogin(user_login: DebtorSession)


    @Query("SELECT * FROM table_session WHERE user_login LIKE :userName")
    fun readUserLogin(userName: String) : DebtorSession

}