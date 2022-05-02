package com.example.testing

import android.app.Application
import android.content.Context
import android.widget.Button
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(app: Application) : AndroidViewModel(app) {
    var numberUserDateAccount= MutableLiveData<Int>(0)
    var accountCount:LiveData<Int>
    var saveButtonEnabled=MutableLiveData<Boolean>(true)
    var nextButtonEnabled= MutableLiveData<Boolean>(true)
    var nextButtonOnShowAccount=MutableLiveData<Boolean>(true)
    var prevButton=MutableLiveData<Boolean>(true)

    init {
        AccountRepository.initDB(app)
        accountCount=AccountRepository.getAccountCount()

    }
   fun saveInformation(accountEntity: AccountEntity){
       AccountRepository.inserAccount(accountEntity)

   }
    fun saveButton(){
        saveButtonEnabled.value=false
    }
    fun nextButtonCreateAcccount(){
        numberUserDateAccount.value=numberUserDateAccount.value?.plus(1)
        nextButtonEnabled.value = false
        saveButtonEnabled.value=true

    }
    fun nextButtonShowAccount(){
        numberUserDateAccount.value=numberUserDateAccount.value?.minus(1)
        if(numberUserDateAccount==accountCount){
            nextButtonOnShowAccount.value=false
        }
    }
}