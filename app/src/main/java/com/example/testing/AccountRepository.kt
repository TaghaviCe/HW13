package com.example.testing

import android.content.Context
import androidx.lifecycle.LiveData

object AccountRepository {
     var db:AppDatabase?=null
     var accountDao:AccountDao?=null
    
     fun initDB(context: Context) {
         db = AppDatabase.getAppDataBase(context)
        accountDao = db?.accountDao()
     }
    fun inserAccount(accountEntity: AccountEntity){
        accountDao?.insertAccount(accountEntity)

    }
    fun deleteAll(){
        accountDao?.deleteAllAccounts()
    }
    fun getAccountCount(): LiveData<Int>? {
        return accountDao?.getAccountCount()
    }
    fun getAccounts():List<AccountEntity>?{
        return accountDao?.getAllAccounts()
    }

}