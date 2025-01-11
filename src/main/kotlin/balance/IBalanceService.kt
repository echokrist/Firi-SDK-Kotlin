package balance

import kotlinx.serialization.json.JsonArray

public interface IBalanceService {
    public suspend fun getUserWalletBalances(): JsonArray
}