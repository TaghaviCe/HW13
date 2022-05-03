package com.example.testing

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AccountDao {
    @Query("SELECT * FROM AccountEntity")
    fun getAllAccounts():List<AccountEntity>

    @Query("SELECT * FROM AccountEntity WHERE cardNumber IN (:cardNum)")
    fun getAccount(cardNum:Int):AccountEntity

    @Insert
    fun insertAccount(account:AccountEntity)

    @Query("DELETE FROM AccountEntity")
    fun deleteAllAccounts()

    @Query("SELECT COUNT(cardNumber) FROM AccountEntity")
    fun getAccountCount(): LiveData<Int>
}