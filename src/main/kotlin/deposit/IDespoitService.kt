package main.deposit

import kotlinx.serialization.json.JsonArray

interface IDespoitService {
    suspend fun getUsersDepositHistory(count: Int? = 100, before: Int? = 0): JsonArray
    suspend fun getUserDepositAddresses(): JsonArray
    suspend fun getUserPendingCurrencyWithdrawal(currency: String): JsonArray
    suspend fun getUserCurrencyAddress(currency: String): JsonArray
}