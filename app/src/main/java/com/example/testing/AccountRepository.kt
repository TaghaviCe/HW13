package com.example.testing

import android.content.Context
import androidx.lifecycle.LiveData

object AccountRepository {
     var db:AppDatabase?=null
    var accountCount=0
    var accountDao:AccountDao?=null
     fun initDB(context: Context) {
         db = AppDatabase.getAppDataBase(context)
        accountDao = db?.accountDao()
     }
    fun inserAccount(accountEntity: AccountEntity){
        return db?.accountDao()!!.insertAccount(accountEntity)

    }
    fun deleteAll(){
        return db?.accountDao()!!.deleteAllAccounts()
    }
    fun getAccountCount(): LiveData<Int> {
        return db?.accountDao()!!.getAccountCount()
    }
    fun setAccountNumber(number:Int){
        accountCount=number
    }
    fun getAccountNumber(): Int {
        return accountCount
    }
}