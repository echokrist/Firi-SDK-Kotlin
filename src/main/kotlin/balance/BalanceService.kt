package balance

import api.IApiClient
import kotlinx.serialization.json.JsonArray
import main.service.BaseService

public class BalanceService(private val apiClientService: IApiClient): BaseService(),IBalanceService
{
    override val endpoint: String = "balances"

    override suspend fun getUserWalletBalances(): JsonArray {
            return this.apiClientService.executeGetRequestAuthorized(this.endpoint)
        }
    }
