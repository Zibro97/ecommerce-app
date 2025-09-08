package com.zibro.ecommerce.domain.repository

import com.zibro.ecommerce.domain.model.AccountInfo
import kotlinx.coroutines.flow.StateFlow

interface AccountRepository {
    fun getAccountInfo() : StateFlow<AccountInfo?>

    suspend fun signInGoogle(accountInfo: AccountInfo)

    suspend fun signOutGoogle()
}