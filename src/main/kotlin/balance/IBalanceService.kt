package balance

import kotlinx.serialization.json.JsonArray

interface IBalanceService {
    suspend fun getUserWalletBalances(): JsonArray
}