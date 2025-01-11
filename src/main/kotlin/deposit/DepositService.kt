package main.deposit

import api.IApiClient
import coin.Coin
import kotlinx.serialization.json.JsonArray
import main.service.BaseService

public class DepositService(private val apiClientService: IApiClient): BaseService(),IDespoitService {
    override val endpoint: String = "deposit"

    override suspend fun getUsersDepositHistory(count: Int?, before: Int?): JsonArray {
        return this.apiClientService.executeGetRequestAuthorized("$endpoint/history?count=${count.toString()}&before=${before.toString()}")
    }

    override suspend fun getUserDepositAddresses(): JsonArray {
        return this.apiClientService.executeGetRequestAuthorized("$endpoint/address")
    }

    override suspend fun getUserPendingCurrencyWithdrawal(currency: String): JsonArray {
        val verifiedCurrency = Coin.valueOf(currency)
        return this.apiClientService.executeGetRequestAuthorized("$verifiedCurrency/withdrawals/pending")
    }

    override suspend fun getUserCurrencyAddress(currency: String): JsonArray {
        val verifiedCurrency = Coin.valueOf(currency)
        return this.apiClientService.executeGetRequestAuthorized("$verifiedCurrency/address")
    }


}