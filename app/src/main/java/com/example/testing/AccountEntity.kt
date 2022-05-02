package com.example.testing

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountEntity (
    @PrimaryKey val cardNumber:String,
    val accBalance:String,
    val accType:AccountType
)