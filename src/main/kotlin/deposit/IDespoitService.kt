package main.deposit

import kotlinx.serialization.json.JsonArray

public interface IDespoitService {
    public suspend fun getUsersDepositHistory(count: Int? = 100, before: Int? = 0): JsonArray
    public suspend fun getUserDepositAddresses(): JsonArray
    public suspend fun getUserPendingCurrencyWithdrawal(currency: String): JsonArray
    public suspend fun getUserCurrencyAddress(currency: String): JsonArray
}