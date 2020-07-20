package com.fluffybros.tujefe.db

import android.accounts.Account
import androidx.lifecycle.LiveData

class AccountRepository(private val accountDao: AccountDAO) {
    val allWords: LiveData<List<AccountTable>> = accountDao.getAlphabetizedAccounts()

    suspend fun insert(accountTable: AccountTable) {
        accountDao.insert(accountTable)
    }
}