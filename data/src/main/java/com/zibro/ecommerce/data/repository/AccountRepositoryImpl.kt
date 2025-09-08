package com.zibro.ecommerce.data.repository

import com.zibro.ecommerce.data.datasource.PreferenceDataSource
import com.zibro.ecommerce.domain.model.AccountInfo
import com.zibro.ecommerce.domain.repository.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
): AccountRepository {
    private val accountInfoFlow = MutableStateFlow(preferenceDataSource.getAccountInfo())

    override fun getAccountInfo(): StateFlow<AccountInfo?> {
        return accountInfoFlow
    }

    override suspend fun signInGoogle(accountInfo: AccountInfo) {
        preferenceDataSource.putAccountInfo(accountInfo)
        accountInfoFlow.emit(accountInfo)
    }

    override suspend fun signOutGoogle() {
        preferenceDataSource.removeAccountInfo()
        accountInfoFlow.emit(null)
    }
}